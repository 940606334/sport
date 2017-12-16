package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.service.SportsUserService;
import cn.yearcon.sport.service.SportsUsersotherService;
import cn.yearcon.sport.service.SysOfficeService;
import cn.yearcon.sport.utils.BarcodeUtil;
import cn.yearcon.sport.utils.CookieUtil;
import cn.yearcon.sport.utils.HttpRequestUtils;
import com.alibaba.fastjson.JSONPath;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        if (cookie==null){
            model.addAttribute("message","请完成认证");
            return "redirect:/reg";
        }
        String vipid=cookie.getValue();
        SportsUsersEntity sportsUsersEntity = sportsUserService.findByVipid(Integer.parseInt(vipid));
        if(sportsUsersEntity==null){
            return "redirect:/wechat/authorize";
        }
        String url=interfaceUrl+"&app_act=user.detail&vipid="+vipid;
        String json= new HttpRequestUtils().getHttp(url);
        String integral = (String) JSONPath.read(json, "$.item.integral");
        sportsUsersEntity.setIntegral(integral);
        model.addAttribute("user",sportsUsersEntity);
        return "index";
    }
    @Value("${interfaceUrl}")
    private String interfaceUrl;

    @Autowired
    private SysOfficeService sysOfficeService;
    @Autowired
    private SportsUserService sportsUserService;
    @RequestMapping(value="/getShoparea",method = RequestMethod.GET)
    public String getShoparea(){
        return "shoparea";
    }
    @Autowired
    private SportsUsersotherService sportsUsersotherService;
    @RequestMapping(value="/getOnecode",method = RequestMethod.GET)
    public String getOneCode(){
        return "barcode";
    }
    @RequestMapping(value = "generateCode")
    public void getCode(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        SportsUsersotherEntity sportsUsersotherEntity=sportsUsersotherService.get(Integer.parseInt(vipid));
        String mobile=sportsUsersotherEntity.getMobile();
        try {
            response.setContentType("image/png");
            ServletOutputStream op =response.getOutputStream();
            BarcodeUtil.generate(mobile,op);
            //ImageIO.write(localBufferedImage,"png",op);
            op.flush();
            response.flushBuffer();
            op.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }


}
