package cn.yearcon.sport.modules.web;

import cn.yearcon.sport.common.json.JsonResult;
import cn.yearcon.sport.common.utils.CookieUtil;
import cn.yearcon.sport.common.utils.MyBeanUtils;
import cn.yearcon.sport.modules.entity.*;
import cn.yearcon.sport.modules.entity.sport.SportsFeedbackEntity;
import cn.yearcon.sport.modules.entity.sport.SportsGroupbuyEntity;
import cn.yearcon.sport.modules.entity.sport.SportsUsersEntity;
import cn.yearcon.sport.modules.entity.sport.SportsWebpageEntity;
import cn.yearcon.sport.modules.entity.sys.SysDictEntity;
import cn.yearcon.sport.modules.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.Gson;
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
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Value("${interfaceUrl}")
    private String interfaceUrl;

    @Autowired
    private SportApiService sportApiService;
    /**
     * 获取积分列表
     */
    @RequestMapping("/getIntegral")
    public String getIntegral(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        String json= sportApiService.getIntegralList(vipid);
        Gson gson=new Gson();
        JsonResult jsonResult=gson.fromJson(json, JsonResult.class);
        logger.info(jsonResult.toString());
        model.addAttribute("list",jsonResult.getLists());
        /*Integer status=(Integer) JSONPath.read(json, "$.status");
        if(status==1){
            JSONArray list = (JSONArray) JSONPath.read(json, "$.lists");
            model.addAttribute("list",list);
        }else{
            logger.debug("没有找到积分列表");
        }*/
        json=sportApiService.getIntegral(vipid);
        String leaveintegral=(String)JSONPath.read(json,"$.msg");
        model.addAttribute("leaveintegral",leaveintegral);
        return "user/integral";
    }
    /**
     * 获取账单信息
     */
    @RequestMapping("/getBill")
    public String getBill(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        //String url=interfaceUrl+"&app_act=retail.list&vipid="+vipid;
        String json= sportApiService.getBillList(vipid);
        Gson gson=new Gson();
        JsonResult jsonResult=gson.fromJson(json, JsonResult.class);
        logger.info(jsonResult.toString());
        model.addAttribute("list",jsonResult.getLists());
        model.addAttribute("message",jsonResult.getMsg());
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
        String pagecontent="";
        if(sportsWebpageEntity!=null){
            pagecontent=sportsWebpageEntity.getPagecontent();
        }
        model.addAttribute("pageContent",pagecontent);
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
        String pagecontent="";
        if(sportsWebpageEntity!=null){
           pagecontent=sportsWebpageEntity.getPagecontent();
        }
        model.addAttribute("pageContent",pagecontent);
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
        //String url=interfaceUrl+"&app_act=voucher.list&vipid="+vipid;
        String json= sportApiService.getCouponList(vipid);
        Integer status=(Integer)JSONPath.read(json,"$.status");
        if(status==1){
            JSONArray list=(JSONArray) JSONPath.read(json,"$.lists");
            model.addAttribute("list",list);
        }else{
            String msg=(String)JSONPath.read(json,"$.msg");
            model.addAttribute("message",msg);
        }
        return "user/coupon";
    }

    /**
     * 会员信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getInfo")
    public String getInfo(HttpServletRequest request,Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        //String url=interfaceUrl+"&app_act=user.detail&vipid="+vipid;
        String json= sportApiService.getVipInfoByid(vipid);
        Map<String,String> infomap = (Map<String,String>) JSONPath.read(json, "$.item");
        model.addAttribute("map",infomap);
        return "user/info";
    }
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(VipInfo vipInfo, RedirectAttributes redirectAttributes){
        //System.out.println(vipInfo);
        //String url=interfaceUrl+"&app_act=user.edit"+vipInfo.toString();
        String json=sportApiService.updateVip(vipInfo.toString());
        Gson gson=new Gson();
        JsonResult jsonResult=gson.fromJson(json, JsonResult.class);
        /*if(jsonResult.getStatus()==1){

        }*/
        redirectAttributes.addAttribute("message",jsonResult.getMsg());
        /*Integer status=(Integer) JSONPath.read(json,"$.status");
        if(status!=1){
            String info=(String)JSONPath.read(json,"$.msg");
            model.addAttribute("message",info);
            return "user/info";
        }*/
        return "redirect:/user/getInfo";
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
    public String saveFeedback(SportsFeedbackEntity sportsFeedbackEntity, HttpServletRequest request,
                               RedirectAttributes redirectAttributes){
        Cookie cookie=CookieUtil.get(request,"vipid");
        Integer vipid=Integer.parseInt(cookie.getValue());
        SportsUsersEntity sportsUsersEntity=sportsUserService.findByVipid(vipid);
        Integer webid=sportsUsersEntity.getWebid();
        sportsFeedbackEntity.setVipid(vipid);
        sportsFeedbackEntity.setWebid(webid);
        sportsFeedbackEntity.setAuditstatus(0);
        sportsFeedbackEntity.setAddtime(new Date());
        sportsFeedbackService.saveFeedback(sportsFeedbackEntity);
        redirectAttributes.addAttribute("message","已成功提交你的建议");
        return "redirect:/index";
    }
}
