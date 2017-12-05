package cn.yearcon.sport.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value="/reg",method = RequestMethod.GET)
    public String reg(){
        return "reg";
    }
}
