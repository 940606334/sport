package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.common.json.JsonResult;
import cn.yearcon.sport.modules.entity.sport.store.StoreIntegral;
import cn.yearcon.sport.modules.entity.sport.user.SportsAuthor;
import cn.yearcon.sport.modules.entity.sport.user.SportsUsersotherEntity;
import cn.yearcon.sport.modules.entity.weixin.WechatUser;
import cn.yearcon.sport.modules.entity.sport.user.SportsUsersEntity;
import cn.yearcon.sport.common.exception.ServiceException;
import cn.yearcon.sport.modules.repository.sport.SportsUsersRepository;
import cn.yearcon.sport.modules.service.store.StoreIntegralService;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.Gson;
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
        //String areaCode=(String) JSONPath.read(json, "$.item.c_customer_id");
        JsonResult jsonResult=new Gson().fromJson(json,JsonResult.class);
        if(jsonResult.getStatus()==0){
            throw new ServiceException("该账号未注册,请注册");
        }
        String areaCode=(String) JSONPath.read(json, "$.item.c_customer_id");
        sportsUsersEntity.setWebid(Integer.parseInt(areaCode));
        sportsUsersRepository.save(sportsUsersEntity);
        //首次认证赠送积分
        SportsAuthor byId = sportsAuthorService.findById(Integer.parseInt(vipid));
        if(byId==null){
            giveIntegral(vipid);
        }
    }
    @Autowired
    private SportsAuthorService sportsAuthorService;

    @Autowired
    private  SportsUsersotherService sportsUserotherService;
    @Autowired
    private StoreIntegralService storeIntegralService;
    /**
     * 根据条件判断是否
     * 赠送积分
     * @param
     */
    private void giveIntegral(String vipid){
        SportsUsersotherEntity entity = sportsUserotherService.findById(Integer.parseInt(vipid));
        String storeid=entity.getStoreid().toString();
        StoreIntegral storeIntegral=storeIntegralService.findById(storeid);
        //类型不是认证会员送积分
        if(!"2".equals(storeIntegral.getType())){
            return;
        }
        //有期限并且开卡时间不在活动时间范围内
        if("1".equals(storeIntegral.getAvailable())){
            if(entity.getAddtime().getTime()<storeIntegral.getBeginDate().getTime()||
                    entity.getAddtime().getTime()>storeIntegral.getEndDate().getTime()){
                return;
            }
        }
        sportApiService.addIntegral(vipid, storeIntegral.getAddIntegral(), "新认证会员送积分");
        SportsAuthor sportsAuthor=new SportsAuthor(Integer.parseInt(vipid),"1");
        sportsAuthorService.save(sportsAuthor);
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
