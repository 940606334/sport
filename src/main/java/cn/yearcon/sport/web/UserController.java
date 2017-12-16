package cn.yearcon.sport.web;

import ch.qos.logback.core.rolling.helper.PeriodicityType;
import cn.yearcon.sport.entity.*;
import cn.yearcon.sport.service.*;
import cn.yearcon.sport.utils.CookieUtil;
import cn.yearcon.sport.utils.HttpRequestUtils;
import cn.yearcon.sport.utils.MyBeanUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.google.gson.JsonArray;
import jdk.net.SocketFlow;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 *
 * @author itguang
 * @create 2017-12-11 13:09
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${interfaceUrl}")
    private String interfaceUrl;
    /**
     * 获取积分列表
     */
    @RequestMapping("/getIntegral")
    public String getIntegral(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        String url=interfaceUrl+"&app_act=integral.list&vipid="+vipid;
        String json= new HttpRequestUtils().getHttp(url);
        Integer status=(Integer) JSONPath.read(json, "$.status");
        if(status==1){
            JSONArray list = (JSONArray) JSONPath.read(json, "$.lists");
            model.addAttribute("list",list);
        }else{
            model.addAttribute("message","找不到记录");
        }
        return "user/integral";
    }
    /**
     * 获取账单信息
     */
    @RequestMapping("/getBill")
    public String getBill(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        String url=interfaceUrl+"&app_act=retail.list&vipid="+vipid;
        String json= new HttpRequestUtils().getHttp(url);
        Integer status=(Integer)JSONPath.read(json,"$.status");
        if(status==1){
            JSONArray list=(JSONArray) JSONPath.read(json,"$.lists");
            if(list.size()==0){
                model.addAttribute("message","账单信息为空");
            }
            model.addAttribute("list",list);
        }else{
            model.addAttribute("message","找不到记录");
        }
        return "user/bill";
    }
    /**
     * 团购申请
     */
    @Autowired
    private SportsGroupbuyService sportsGroupbuyService;
    @Autowired
    private SportsUserService sportsUserService;
    @RequestMapping("/togroupbuy")
    public String toGroupBuy(HttpServletRequest request, Model model){
        List<SportsGroupbuyEntity> list=sportsGroupbuyService.findbyVipid(request);
        //System.out.println(list);
        model.addAttribute("list",list);
        return "user/groupbuy";
    }
    @Autowired
    private SportsWebpageService sportsWebpageService;
    @RequestMapping("/editGroupbuy")
    public String editGroupbuy(Integer id,Model model,HttpServletRequest request){
        SportsGroupbuyEntity sportsGroupbuyEntity=sportsGroupbuyService.findById(id);
        model.addAttribute("groupbuy",sportsGroupbuyEntity);
        Cookie cookie=CookieUtil.get(request,"vipid");
        Integer vipid=Integer.parseInt(cookie.getValue());
        SportsUsersEntity sportsUsersEntity=sportsUserService.findByVipid(vipid);
        model.addAttribute("user",sportsUsersEntity);
        Integer webid=sportsUsersEntity.getWebid();
        SportsWebpageEntity sportsWebpageEntity=sportsWebpageService.findByWebidAndPagecode(webid,groupbuyPagecode);
        model.addAttribute("pageContent",sportsWebpageEntity.getPagecontent());
       return "user/groupbuyedit";
    }
    @Value("${groupbuyPagecode}")
    private String groupbuyPagecode;
    @RequestMapping("/addGroupbuy")
    public String addGroupbuy(Model model,HttpServletRequest request){
        Cookie cookie=CookieUtil.get(request,"vipid");
        Integer vipid=Integer.parseInt(cookie.getValue());
        SportsUsersEntity sportsUsersEntity=sportsUserService.findByVipid(vipid);
        model.addAttribute("user",sportsUsersEntity);
        Integer webid=sportsUsersEntity.getWebid();
        SportsWebpageEntity sportsWebpageEntity=sportsWebpageService.findByWebidAndPagecode(webid,groupbuyPagecode);
        model.addAttribute("pageContent",sportsWebpageEntity.getPagecontent());
        return "user/groupbuyadd";
    }
    @RequestMapping(value = "/saveGroupbuy",method = RequestMethod.POST)
    public String saveGroupbuy(SportsGroupbuyEntity sportsGroupbuyEntity){
        //System.out.println(sportsGroupbuyEntity);
        //System.out.println(sportsGroupbuyEntity.getAddtime());
        Integer id=sportsGroupbuyEntity.getId();
        if(id==null){
            sportsGroupbuyEntity.setAddtime(new Date());
            sportsGroupbuyEntity.setAuditstatus("0");
            sportsGroupbuyService.save(sportsGroupbuyEntity);
            return "redirect:/user/togroupbuy";
        }
        SportsGroupbuyEntity entity=sportsGroupbuyService.findById(id);
        try {
            MyBeanUtils.copyBeanNotNull2Bean(sportsGroupbuyEntity,entity);//将编辑表单中的非NULL值覆盖数据库记录中的值
        } catch (Exception e) {
            e.printStackTrace();
        }
        sportsGroupbuyService.save(entity);
        return "redirect:/user/togroupbuy";
    }
    /**
     * 获取优惠券
     */
    @RequestMapping("/getCoupon")
    public String getCoupon(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        String url=interfaceUrl+"&app_act=voucher.list&vipid="+vipid;
        String json= new HttpRequestUtils().getHttp(url);
        Integer status=(Integer)JSONPath.read(json,"$.status");
        if(status==1){
            JSONArray list=(JSONArray) JSONPath.read(json,"$.lists");
            if(list.size()==0){
                model.addAttribute("message","无可用的优惠券");
            }
            model.addAttribute("list",list);
        }else{
            model.addAttribute("message","找不到记录");
        }
        return "user/coupon";
    }
    @RequestMapping("/getInfo")
    public String getInfo(HttpServletRequest request,Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        String url=interfaceUrl+"&app_act=user.detail&vipid="+vipid;
        String json= new HttpRequestUtils().getHttp(url);
        Map<String,String> infomap = (Map<String,String>) JSONPath.read(json, "$.item");
        model.addAttribute("map",infomap);
        return "user/info";
    }
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(VipInfo vipInfo,HttpServletRequest request,Model model){
        //System.out.println(vipInfo);
        String url=interfaceUrl+"&app_act=user.edit"+vipInfo.toString();
        String json=new HttpRequestUtils().postHttp(url);
        Integer status=(Integer) JSONPath.read(json,"$.status");
        if(status==0){
            String info=(String)JSONPath.read(json,"$.info");
            model.addAttribute("message",info);
            return "user/getInfo";
        }
        return "redirect:/index";
    }
    @RequestMapping(value = "/feedback")
    public String feedback(){
        return "user/feedback";
    }
    @Autowired
    private SysDicService sysDicService;
    @RequestMapping(value="getType")
    @ResponseBody
    public List<SysDictEntity> getType(String type){
        List<SysDictEntity> list=sysDicService.findByType(type);
        return list;
    }
    @Autowired
    private SportsFeedbackService sportsFeedbackService;
    @RequestMapping(value = "/saveFeedback",method = RequestMethod.POST)
    public String saveFeedback(SportsFeedbackEntity sportsFeedbackEntity,HttpServletRequest request){
        Cookie cookie=CookieUtil.get(request,"vipid");
        Integer vipid=Integer.parseInt(cookie.getValue());
        SportsUsersEntity sportsUsersEntity=sportsUserService.findByVipid(vipid);
        Integer webid=sportsUsersEntity.getWebid();
        sportsFeedbackEntity.setVipid(vipid);
        sportsFeedbackEntity.setWebid(webid);
        sportsFeedbackEntity.setAuditstatus(0);
        sportsFeedbackEntity.setAddtime(new Date());
        sportsFeedbackService.saveFeedback(sportsFeedbackEntity);
        return "redirect:/index";
    }
}
