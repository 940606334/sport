package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.RegVip;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.exception.ServiceException;
import cn.yearcon.sport.json.JsonResult;
import cn.yearcon.sport.repository.SportsUsersotherRepository;
import cn.yearcon.sport.utils.CookieUtil;
import cn.yearcon.sport.utils.HttpRequestUtils;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 会员认证业务层
 *
 * @author itguang
 * @create 2017-12-07 13:58
 **/
@Service
public class SportsUsersotherService {


    @Autowired
    private SportsUsersotherRepository sportsUsersotherRepository;
    @Autowired
    private SportsUserService sportsUserService;

    /**注册会员*/
    public void regvip(SportsUsersotherEntity entity,HttpServletResponse response){
        if(entity==null){
            throw new ServiceException("会员信息为空");
        }

        String json=postInterface(entity);
        System.out.println("返回json信息:"+json);

        JsonResult jsonResult=new Gson().fromJson(json, JsonResult.class);

        if(jsonResult.getStatus()==0) {//状态为0注册失败
            throw new ServiceException(jsonResult.getInfo());
        }else if(jsonResult.getStatus()==1){//1注册成功
            String vipid=jsonResult.getInfo();
            CookieUtil.set(response,"vipid",vipid);
            entity.setVipid(Integer.parseInt(vipid));
            entity.setAddtime(new Date());
            entity.setCarttype(cardtype);
            entity.setStorename(getStorename(vipid));
            sportsUsersotherRepository.save(entity);
        }


    }

    /**
     * 获取店名
     * @param vipid
     * @return
     */
    public String getStorename(String vipid){
        String url=interfaceUrl+"&app_act=user.detail&vipid="+vipid;
        String json= new HttpRequestUtils().getHttp(url);
        return (String) JSONPath.read(json, "$.item.storename");
    }

    /**
     *根据id查询
     * @param vipid
     * @return
     */
    public SportsUsersotherEntity get(Integer vipid){

        return sportsUsersotherRepository.findOne(vipid);
    }

    @Value("${interfaceUrl}")
    private String interfaceUrl;
    @Value("${cardtype}")
    private String cardtype;
    public String postInterface(SportsUsersotherEntity entity){
        RegVip regVip=new RegVip();
        regVip.setV_birthday(entity.getVipbirthday());
        regVip.setV_code(entity.getCheckcode());
        regVip.setV_mobil(entity.getMobile());
        regVip.setV_name(entity.getTruename());
        regVip.setV_passwd(entity.getVippassword());
        regVip.setV_store(entity.getStoreid());
        String sex=entity.getVipsex();
        if("1".equals(sex)){
            regVip.setV_sex("M");
        }else{
            regVip.setV_sex("W");
        }
        System.out.println(regVip.toString());
        String url=interfaceUrl+"&app_act=user.reg"+ regVip.toString();

        return new HttpRequestUtils().postHttp(url);
    }


    public SportsUsersotherEntity findByMObile(String mobile){
        return sportsUsersotherRepository.findByMobile(mobile);
    }
}
