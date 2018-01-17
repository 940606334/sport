package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.SportsSmscodeEntity;
import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.json.JsonResult;
import cn.yearcon.sport.service.*;
import cn.yearcon.sport.utils.CookieUtil;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


@Controller
public class LoginController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Value("${interfaceUrl}")
    private String interfaceUrl;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie=CookieUtil.get(request,"regorloginUrl");
        if(cookie==null){
            CookieUtil.set(response,"regorloginUrl","/login");
            return "redirect:/wechat/authorize";
        }
        CookieUtil.set(response,"regorloginUrl","/login",0);
        return "sport/login";
    }

    @Autowired
    private SportsSmscodeService sportsSmscodeService;
    @Autowired
    private SportApiService sportApiService;

    @RequestMapping(value="getCode",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getCode(String mobile){
       return  sportsSmscodeService.getCheckCode(mobile);
    }
    @Autowired
    private SportsUsersotherService sportsUsersotherService;


    @RequestMapping(value = "getVipidByMobile",method = RequestMethod.POST)
    public String getVipidByMobile(String mobile, String checkcode,
                                   HttpServletResponse response,RedirectAttributes redirectAttributes){

        //验证验证码
        SportsSmscodeEntity sportsSmscodeEntity=sportsSmscodeService.findByMobile(mobile);
        if(sportsSmscodeEntity==null){
            redirectAttributes.addAttribute("message","验证码不正确");
            CookieUtil.set(response,"regorloginUrl","/login");
            return "redirect:/login";
        }
        if(!sportsSmscodeEntity.getCode().equals(checkcode)){
            redirectAttributes.addAttribute("message","验证码不正确");
            CookieUtil.set(response,"regorloginUrl","/login");
            return "redirect:/login";
        }
        //验证手机号有没有注册过
        SportsUsersotherEntity sportsUsersotherEntity=sportsUsersotherService.findByMObile(mobile);
        Integer vipid=null;
        if(sportsUsersotherEntity==null){
            logger.info("根据手机号查询vipid");
            String json=sportApiService.getVipidByMobile(mobile);
            Integer status=(Integer) JSONPath.read(json,"$.status");
            if(status==0){
                redirectAttributes.addAttribute("message","尚未开卡请注册");
                CookieUtil.set(response,"regorloginUrl","/login");
                return "redirect:/login";
            }
            vipid=(Integer) JSONPath.read(json,"$.vipid");
            //CookieUtil.set(response,"vipid",vipid.toString());
            //保存用户信息
            logger.info("保存vipid为"+vipid+"的用户信息");
            sportsUsersotherService.saveByVipid(vipid);
        }else{
            vipid=sportsUsersotherEntity.getVipid();
        }
        //验证vipid是否为空
        if(vipid!=null){
            CookieUtil.set(response,"vipid",vipid.toString());
            return "redirect:/wechat/authorize";
        }else{
            redirectAttributes.addAttribute("message","认证失败");
            CookieUtil.set(response,"regorloginUrl","/login");
            return "redirect:/login";
        }

       /* SportsUsersEntity sportsUsersEntity = sportsUserService.findByVipid(vipid);
        if(sportsUsersEntity==null){
            return "redirect:/wechat/authorize";
        }
        //验证机构id
        String json=sportApiService.getVipInfoByid(vipid);
        String areaCode=(String) JSONPath.read(json,"$.item.c_customer_id");
        Map<String,String> map=sportsWxService.getAppid(request);
        String webid= map.get("webid");
        if(!webid.equals(areaCode)){
            String name=map.get("name");
            String name1=sysOfficeService.findNameByCode(areaCode);
            logger.info("该域名所在的webid为"+webid+",该账号的webid为"+areaCode);
            redirectAttributes.addAttribute("message","您的会员卡不在“"+name+"”服务区，请关注 “"+name1+"”");
            return "redirect:/login";
        }
        return "redirect:/index";*/
    }


}
