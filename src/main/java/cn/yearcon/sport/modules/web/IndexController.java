package cn.yearcon.sport.modules.web;

import cn.yearcon.sport.modules.entity.sport.SportsUsersEntity;
import cn.yearcon.sport.modules.entity.sport.SportsUsersotherEntity;
import cn.yearcon.sport.modules.service.SportApiService;
import cn.yearcon.sport.modules.service.SportsUserService;
import cn.yearcon.sport.modules.service.SportsUsersotherService;
import cn.yearcon.sport.modules.service.SysOfficeService;
import cn.yearcon.sport.common.utils.BarcodeUtil;
import cn.yearcon.sport.common.utils.CookieUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    SportApiService sportApiService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model,HttpServletResponse response){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        SportsUsersEntity sportsUsersEntity = sportsUserService.findByVipid(Integer.parseInt(vipid));
        if(sportsUsersEntity==null){
            return "redirect:/wechat/authorize";
        }
        String json=sportApiService.getVipInfoByid(vipid);
        logger.info("会员信息:"+json);
        String integral = (String) JSONPath.read(json, "$.item.integral");
        sportsUsersEntity.setIntegral(integral);
        json=sportApiService.getCouponList(vipid);
        logger.info("购物券列表:"+json);
        Integer status=(Integer)JSONPath.read(json,"$.status");
        if(status==1){
            JSONArray list=(JSONArray) JSONPath.read(json,"$.lists");
            sportsUsersEntity.setCoupon(list.size()+"");
        }else{
            sportsUsersEntity.setCoupon("0");
        }
        model.addAttribute("user",sportsUsersEntity);
        cookie=CookieUtil.get(request,"url");
        if (cookie!=null){
            String url=cookie.getValue();
            CookieUtil.set(response,"url",url,0);
            if(url.endsWith("/index")){
                return "sport/index";
            }
            return "redirect:"+url;
        }
        return "sport/index";
    }
    @Value("${interfaceUrl}")
    private String interfaceUrl;

    @Autowired
    private SysOfficeService sysOfficeService;
    @Autowired
    private SportsUserService sportsUserService;
    @RequestMapping(value="/getShoparea",method = RequestMethod.GET)
    public String getShoparea(){
        return "sport/shoparea";
    }
    @Autowired
    private SportsUsersotherService sportsUsersotherService;
    @RequestMapping(value="/getOnecode",method = RequestMethod.GET)
    public String getOneCode(){
        return "sport/barcode";
    }

    /**
     * 生成一维码
     * @param request
     * @param response
     */
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
