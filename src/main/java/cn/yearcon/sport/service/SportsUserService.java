package cn.yearcon.sport.service;

import cn.yearcon.sport.dto.WechatUser;
import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.exception.ServiceException;
import cn.yearcon.sport.json.JsonResult;
import cn.yearcon.sport.repository.SportsUsersRepository;
import com.alibaba.fastjson.JSONPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 会员认证业务层
 *
 * @author itguang
 * @create 2017-12-08 13:38
 **/
@Service
public class SportsUserService {

    @Autowired
    private SportsUsersRepository sportsUsersRepository;
    @Autowired
    private SportApiService sportApiService;

    public void checkvip(WechatUser wechatUser,String vipid) {
        if (wechatUser==null){
            throw new ServiceException("对象不能为空");
        }
        if(vipid==null ||"".equals(vipid)){
            throw new ServiceException("未开卡或者为认证");
        }
        SportsUsersEntity sportsUsersEntity=new SportsUsersEntity();
        sportsUsersEntity.setVipid(Integer.parseInt(vipid));
        sportsUsersEntity.setNickname(wechatUser.getNickname());
        sportsUsersEntity.setHeadimgurl(wechatUser.getHeadImgUrl());
        sportsUsersEntity.setOpenid(wechatUser.getOpenId());
        sportsUsersEntity.setUnionid(wechatUser.getUnionId());
        sportsUsersEntity.setCity(wechatUser.getCity());
        sportsUsersEntity.setProvince(wechatUser.getProvince());
        String sex=wechatUser.getSex();
        if("男".equals(sex)){
            sportsUsersEntity.setSex("1");
        }else{
            sportsUsersEntity.setSex("2");
        }

        sportsUsersEntity.setPrivilege(wechatUser.getPrivilege());
        sportsUsersEntity.setAddtime(new Date());
        String json=sportApiService.getVipInfoByid(vipid);
        String areaCode=(String) JSONPath.read(json, "$.item.c_customer_id");
        sportsUsersEntity.setWebid(Integer.parseInt(areaCode));
        sportsUsersRepository.save(sportsUsersEntity);

    }
    /**根据vipid查询会员信息*/
    public SportsUsersEntity findByVipid(Integer vipid){
        return sportsUsersRepository.findOne(vipid);
    }

    /**根据openid查询会员信息*/
    public SportsUsersEntity findByOpenid(String openid){
        return sportsUsersRepository.findByOpenid(openid);
    }

    public void deleteById(Integer vipid){
        sportsUsersRepository.delete(vipid);
    }
}
