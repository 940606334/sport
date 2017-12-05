package cn.yearcon.sport.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        System.out.println("万事皆因忙中错，好人半自苦中来");

        return "index";
    }

    @RequestMapping(value="/test8",method = RequestMethod.GET)
    public String test(Map<String ,Object> map){

        return "redirect:"+"index";
    }
}
