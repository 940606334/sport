package cn.yearcon.sport.modules.web;

import cn.yearcon.sport.modules.entity.sport.user.SportsGivereward;
import cn.yearcon.sport.modules.service.sport.SportsGiverewardService;
import cn.yearcon.sport.common.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author itguang
 * @create 2018-01-17 16:56
 **/
@Controller
@RequestMapping(value = "user")
public class GiverewardController {

    @RequestMapping(value = "givereward")
    public String givereward(HttpServletRequest request, SportsGivereward sportsGivereward, Model model){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        sportsGivereward.setVipid(Integer.parseInt(vipid));
        model.addAttribute("sportsGivereward",sportsGivereward);
        return "user/givereward";
    }
    @Autowired
    private SportsGiverewardService sportsGiverewardService;

    @RequestMapping(value="saveGivereward")
    public String saveGivereward(SportsGivereward sportsGivereward,Model model){
        sportsGiverewardService.save(sportsGivereward);
        model.addAttribute("sportsGivereward",sportsGivereward);
        return "user/givereward";
    }
}
