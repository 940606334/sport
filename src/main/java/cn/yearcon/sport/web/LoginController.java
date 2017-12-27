package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.SportsSmscodeEntity;
import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.json.JsonResult;
import cn.yearcon.sport.service.*;
import cn.yearcon.sport.utils.CookieUtil;
import cn.yearcon.sport.utils.HttpRequestUtils;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {
    @Autowired
    private SysOfficeService sysOfficeService;
    @Autowired
    private SportsWxService sportsWxService;
    @Autowired
    private SportsUserService sportsUserService;
    @Value("${interfaceUrl}")
    private String interfaceUrl;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model,HttpServletResponse response){
        Cookie cookie=CookieUtil.get(request,"vipid");
        if(cookie==null){
            return "sport/login";
        }else{
            String vipid=cookie.getValue();
            SportsUsersEntity sportsUsersEntity=sportsUserService.findByVipid(Integer.parseInt(vipid));
            if(sportsUsersEntity==null){
                return "redirect:/wechat/authorize";
            }
            model.addAttribute("user",sportsUsersEntity);
            return "redirect:/index";
        }

    }

    @Autowired
    private SportsSmscodeService sportsSmscodeService;

    @RequestMapping(value="getCode",method = RequestMethod.POST)
    @ResponseBody
    public String getCode(String mobile){
        String url=interfaceUrl+"&app_act=code.get&v_mobil="+mobile;
        String json=new HttpRequestUtils().getHttp(url);
        Gson gson=new Gson();
        JsonResult jsonResult=gson.fromJson(json,JsonResult.class);
        if(jsonResult.getStatus()==1){
            SportsSmscodeEntity sportsSmscodeEntity=sportsSmscodeService.findByMobile(mobile);
            if(sportsSmscodeEntity==null){
                sportsSmscodeEntity=new SportsSmscodeEntity();
                sportsSmscodeEntity.setMobile(mobile);
            }
            sportsSmscodeEntity.setCode(jsonResult.getInfo());
            sportsSmscodeService.saveMobile(sportsSmscodeEntity);
        }
        return json;
    }
    @Autowired
    private SportsUsersotherService sportsUsersotherService;
    @RequestMapping(value = "getVipidByMobile",method = RequestMethod.POST)
    public String getVipidByMobile(String mobile, String checkcode,HttpServletRequest request, Model model,
                                   HttpServletResponse response){

        SportsUsersotherEntity sportsUsersotherEntity=sportsUsersotherService.findByMObile(mobile);
        if(sportsUsersotherEntity==null){
            model.addAttribute("message","请注册");
            return "sport/reg";
        }
        SportsSmscodeEntity sportsSmscodeEntity=sportsSmscodeService.findByMobile(mobile);
        if(!sportsSmscodeEntity.getCode().equals(checkcode)){
            model.addAttribute("message","验证码不正确");
            return "sport/login";
        }
        String vipid=sportsUsersotherEntity.getVipid()+"";
        CookieUtil.set(response,"vipid",vipid);
        SportsUsersEntity sportsUsersEntity = sportsUserService.findByVipid(sportsUsersotherEntity.getVipid());
        if(sportsUsersEntity==null){
            return "redirect:/wechat/authorize";
        }
        model.addAttribute("user",sportsUsersEntity);
        return "redirect:/index";
    }


}
