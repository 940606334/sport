package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.SportsWebpageEntity;
import cn.yearcon.sport.service.SportsWebpageService;
import cn.yearcon.sport.service.SportsWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Map;

/**
 * 自定义页面
 *
 * @author itguang
 * @create 2017-12-14 13:01
 **/
@Controller
@RequestMapping("/page")
public class PageController {
    @Autowired
    private SportsWxService sportsWxService;

    @Autowired
    private SportsWebpageService sportsWebpageService;
    @Value("${aboutusPagecode}")
    private String aboutusPagecode;
    @Value("${privilegePagecode}")
    private String privilegePagecode;
    @Value("${classroomPagecode}")
    private String classroomPagecode;
    @RequestMapping(value = "aboutus")
    public String toAboutus(HttpServletRequest request){
       String pageurl=getUrl(request,aboutusPagecode);
        return "redirect:"+pageurl;
    }
    @RequestMapping(value = "privilege")
    public String toPrivilege(HttpServletRequest request){
        String pageurl=getUrl(request,privilegePagecode);
        return "redirect:"+pageurl;
    }
    @RequestMapping(value = "classroom")
    public String toClassroom(HttpServletRequest request){
        String pageurl=getUrl(request,classroomPagecode);
        return "redirect:"+pageurl;
    }
    public String getUrl(HttpServletRequest request,String pagecode){
        Map<String,String> map=sportsWxService.getAppid(request);
        String webid=map.get("webid");
        SportsWebpageEntity sportsWebpageEntity=sportsWebpageService.findByWebidAndPagecode(Integer.parseInt(webid),pagecode);
        String pageurl=sportsWebpageEntity.getPageurl();
        return pageurl;
    }
}
