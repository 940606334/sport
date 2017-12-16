package cn.yearcon.sport.service;

import cn.yearcon.sport.dto.WechatUser;
import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.exception.ServiceException;
import cn.yearcon.sport.repository.SportsUsersRepository;
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

    public SportsUsersEntity checkvip(WechatUser wechatUser,String vipid,String webid) {
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
        sportsUsersEntity.setWebid(Integer.parseInt(webid));
        sportsUsersRepository.save(sportsUsersEntity);
        return sportsUsersEntity;

    }
    /**根据vipid查询会员信息*/
    public SportsUsersEntity findByVipid(Integer vipid){
        return sportsUsersRepository.findOne(vipid);
    }
}
