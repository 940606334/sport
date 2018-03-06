package cn.yearcon.sport.modules.web;

import cn.yearcon.sport.modules.service.SportsUserService;
import cn.yearcon.sport.common.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试cookie
 *
 * @author itguang
 * @create 2018-01-04 8:53
 **/
@Controller
public class CookieController {
    @Autowired
    SportsUserService sportsUserService;
    @RequestMapping(value = "/delId")
    public String deleteCookie(HttpServletResponse response, HttpServletRequest request,RedirectAttributes redirectAttributes){
        Cookie cookie=CookieUtil.get(request,"vipid");
        if(cookie!=null){
            String vipid=cookie.getValue();
            sportsUserService.deleteById(Integer.parseInt(vipid));
        }
        CookieUtil.set(response,"vipid",null,0);
        redirectAttributes.addAttribute("message","已成功解绑微信");
        CookieUtil.set(response,"regorloginUrl","/login");
        return "redirect:/login";
    }
}
