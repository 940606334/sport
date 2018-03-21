package cn.yearcon.sport.modules.web;

import cn.yearcon.sport.modules.entity.sport.user.SportsExtrainfo;
import cn.yearcon.sport.modules.entity.sport.user.SportsGivereward;
import cn.yearcon.sport.modules.service.sport.SportApiService;
import cn.yearcon.sport.modules.service.sport.SportsExtraInfoService;
import cn.yearcon.sport.modules.service.sport.SportsGiverewardService;
import cn.yearcon.sport.common.utils.CookieUtil;
import cn.yearcon.sport.modules.service.sport.SportsWxService;
import com.alibaba.fastjson.JSONPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author itguang
 * @create 2018-01-17 16:56
 **/
@Controller
@RequestMapping(value = "vip")
public class VipController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SportsWxService sportsWxService;
    @RequestMapping(value = "givereward")
    public String givereward(HttpServletRequest request, SportsGivereward sportsGivereward, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        logger.info("-----------------打赏的会员id为="+vipid);
        sportsGivereward.setVipid(Integer.parseInt(vipid));
        logger.info(sportsGivereward.toString());
        Map<String, String> appid = sportsWxService.getAppid(request);
        String webid=appid.get("webid");
        sportsGivereward.setWebid(Integer.parseInt(webid));
        model.addAttribute("sportsGivereward",sportsGivereward);
        String json=sportApiService.getVipInfoByid(vipid);
        logger.info("会员信息:"+json);
        String integral = (String) JSONPath.read(json, "$.item.integral");
        model.addAttribute("integral",integral);
        return "user/givereward2";
    }
    @Autowired
    private SportsGiverewardService sportsGiverewardService;


    @Autowired
    private SportApiService sportApiService;

    @RequestMapping(value="saveGivereward")
    public String saveGivereward(SportsGivereward sportsGivereward){
        logger.info(sportsGivereward.toString());
        sportsGiverewardService.save(sportsGivereward);
        sportApiService.costIntegral(sportsGivereward.getVipid().toString(),sportsGivereward.getGiveintegral(),"打赏积分","");
        return "user/givereward3";
    }

    @Autowired
    private SportsExtraInfoService sportsExtraInfoService;

    @RequestMapping(value="addInfo")
    public String addInfo(HttpServletRequest request, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        SportsExtrainfo sportsExtrainfo=sportsExtraInfoService.findById(Integer.parseInt(vipid));
        if (sportsExtrainfo==null){
            sportsExtrainfo=new SportsExtrainfo();
            sportsExtrainfo.setVipid(Integer.parseInt(vipid));
        }
        model.addAttribute("sportsExtrainfo",sportsExtrainfo);
        return "extra/addInfo";
    }
    @RequestMapping(value = "saveExtraInfo")
    public String saveExtraInfo(HttpServletRequest request, SportsExtrainfo sportsExtrainfo){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        SportsExtrainfo entity=sportsExtraInfoService.findById(Integer.parseInt(vipid));
        if(entity==null){
            sportApiService.addIntegral(vipid,3,"完善资料");
        }
        sportsExtraInfoService.save(sportsExtrainfo);
        return "redirect:/index";
    }
}
