package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.SportsUsersotherEntity;
import cn.yearcon.sport.entity.SysOfficeEntity;
import cn.yearcon.sport.service.SportsUsersotherService;
import cn.yearcon.sport.service.SysOfficeService;
import cn.yearcon.sport.utils.CookieUtil;
import cn.yearcon.sport.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author itguang
 * @create 2017-12-06 17:17
 **/
@Controller
public class RegController {
    @Value("${interfaceUrl}")
    private String interfaceUrl;
    @Autowired
    private SysOfficeService sysOfficeService;
    @Autowired
    private SportsUsersotherService sportsUsersotherService;
    //注册页面
    @RequestMapping(value="/reg",method = RequestMethod.GET)
    public String reg(){
        return "sport/reg";
    }
    //获取商店列表
    @RequestMapping(value="getStore")
    @ResponseBody
    public String getStore(HttpServletRequest request, String coordinate){

       System.out.println("coordinate="+coordinate);

        //1.得到请求的 服务器域名
        String serverName = request.getServerName();
        SysOfficeEntity officeEntity = sysOfficeService.findOneByAddress(serverName);
        String webid=officeEntity.getCode();//获取机构id
        System.out.println(webid);
        String url=interfaceUrl+"&app_act=store.list&v_cus_id="+webid+"&coordinate="+coordinate;
        //String str="{\"status\":1,\"lists\":[{\"id\":\"2551\",\"name\":\"总部备单仓\",\"address\":\"福建省泉州市晋江县新塘街道\",\"license\":\"\",\"phone\":\"\",\"coordinate\":\"118.614594,24.766896\",\"distance\":\"7000.40\"}]}";
        //System.out.println(str);
        return new HttpRequestUtils().getHttp(url);
        //return str;
    }
    /**注册会员*/
    @RequestMapping(value = "/regvip",method = RequestMethod.POST)
   public String regvip(SportsUsersotherEntity entity, Model model,
                              HttpServletResponse response,
                              HttpServletRequest request){
        String serverName = request.getServerName();
        String url = "http://" + serverName + "/sport/wechat/authorize";
        //获取手机
        String mobile=entity.getMobile();
        SportsUsersotherEntity sportsUsersotherEntity=sportsUsersotherService.findByMObile(mobile);
        if(sportsUsersotherEntity!=null){
            String vipid=sportsUsersotherEntity.getVipid()+"";
            CookieUtil.set(response,"vipid",vipid);
            return "redirect:/login";
        }
        try {
            sportsUsersotherService.regvip(entity,response);
            //model.addAttribute("message","注册成功");
            return url;
        }catch (Exception e) {
            model.addAttribute("message",e.getMessage());
            //System.out.println(e.getMessage());
            return "redirect:/reg";
        }

    }
}
