package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.SportsSmscodeEntity;
import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.entity.SysOfficeEntity;
import cn.yearcon.sport.json.JsonResult;
import cn.yearcon.sport.service.SportApiService;
import cn.yearcon.sport.service.SportsSmscodeService;
import cn.yearcon.sport.service.SportsUsersotherService;
import cn.yearcon.sport.service.SysOfficeService;
import cn.yearcon.sport.utils.CookieUtil;
import com.alibaba.fastjson.JSONPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author itguang
 * @create 2017-12-06 17:17
 **/
@Controller
public class RegController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Value("${interfaceUrl}")
    private String interfaceUrl;
    @Autowired
    private SysOfficeService sysOfficeService;
    @Autowired
    private SportsUsersotherService sportsUsersotherService;

    //注册页面
    @RequestMapping(value="/reg",method = RequestMethod.GET)
    public String reg(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie=CookieUtil.get(request,"regorloginUrl");
        if(cookie==null){
            CookieUtil.set(response,"regorloginUrl","/reg");
            return "redirect:/wechat/authorize";
        }
        CookieUtil.set(response,"regorloginUrl","/reg",0);
        return "sport/reg";
        //return "sport/reg";
    }

    //获取商店列表
    @RequestMapping(value="getStore")
    @ResponseBody
    public String getStore(HttpServletRequest request, String coordinate){
        logger.info("coordinate="+coordinate);
        //1.得到请求的 服务器域名
        String serverName = request.getServerName();
        SysOfficeEntity officeEntity = sysOfficeService.findOneByAddress(serverName);
        String webid=officeEntity.getCode();//获取机构id
        logger.info("webid="+webid);
        //String url=interfaceUrl+"&app_act=store.list&v_cus_id="+webid+"&coordinate="+coordinate;
        return sportApiService.getStoreInfo(webid,coordinate);
    }
    @Autowired
    private SportApiService sportApiService;
    @Autowired
    private SportsSmscodeService sportsSmscodeService;

    @RequestMapping(value = "checkmobile")
    @ResponseBody
    public JsonResult checkMobile(String mobile,HttpServletResponse response){
        String json=sportApiService.getVipidByMobile(mobile);
        Integer status=(Integer) JSONPath.read(json,"$.status");
        JsonResult jsonResult=new JsonResult(status);
        if(status==1){
            jsonResult.setMsg("该手机号已经注册过了,请点击 '我已开卡'");
            String vipid=(String) JSONPath.read(json,"$.msg");
            CookieUtil.set(response,"vipid",vipid);
            //保存用户信息
            sportsUsersotherService.saveByVipid(Integer.parseInt(vipid));
        }
        return jsonResult;
    }


    /**注册会员*/
    @RequestMapping(value = "/regvip",method = RequestMethod.POST)
   public String regvip(SportsUsersotherEntity entity, Model model,
                              HttpServletResponse response,
                          RedirectAttributes redirectAttributes){
        //获取手机号
        String mobile=entity.getMobile();
        //验证验证码
        //验证验证码
        SportsSmscodeEntity sportsSmscodeEntity=sportsSmscodeService.findByMobile(mobile);
        if(sportsSmscodeEntity==null){
            redirectAttributes.addAttribute("message","验证码不正确");
            CookieUtil.set(response,"regorloginUrl","/reg");
            return "redirect:/reg";
        }
        if(!sportsSmscodeEntity.getCode().equals(entity.getCheckcode())){
            redirectAttributes.addAttribute("message","验证码不正确");
            CookieUtil.set(response,"regorloginUrl","/reg");
            return "redirect:/reg";
        }
        //验证该手机号时候注册过了
        String json=sportApiService.getVipidByMobile(mobile);
        Integer status=(Integer) JSONPath.read(json,"$.status");
        if(status==1){
            redirectAttributes.addAttribute("message","该手机号已经注册过了,请点击 '我已开卡'");
            Integer vipid=(Integer) JSONPath.read(json,"$.vipid");
            CookieUtil.set(response,"vipid",vipid.toString());
            //保存用户信息
            sportsUsersotherService.saveByVipid(vipid);
            CookieUtil.set(response,"regorloginUrl","/reg");
            return "redirect:/reg";
        }
        //验证该手机号是否存在,若存在则跳转到微信授权
        SportsUsersotherEntity sportsUsersotherEntity=sportsUsersotherService.findByMObile(mobile);
        if(sportsUsersotherEntity!=null){
            String vipid=sportsUsersotherEntity.getVipid()+"";
            CookieUtil.set(response,"vipid",vipid);
            return "redirect:/wechat/authorize";
        }
        try {
            //注册会员
            sportsUsersotherService.regvip(entity,response);
            return "redirect:/wechat/authorize";
        }catch (Exception e) {
            redirectAttributes.addAttribute("message",e.getMessage());
            CookieUtil.set(response,"regorloginUrl","/reg");
            return "redirect:/reg";
        }

    }
}
