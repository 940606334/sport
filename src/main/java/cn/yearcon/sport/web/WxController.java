package cn.yearcon.sport.web;

import cn.yearcon.sport.dto.WechatUser;
import cn.yearcon.sport.entity.SportsSecretEntity;
import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.enums.ResultEnum;
import cn.yearcon.sport.exception.SportException;
import cn.yearcon.sport.json.WxResult;
import cn.yearcon.sport.service.SportsSecretService;
import cn.yearcon.sport.service.SportsUserService;
import cn.yearcon.sport.service.SportsWxService;
import cn.yearcon.sport.service.SysOfficeService;
import cn.yearcon.sport.utils.CookieUtil;
import cn.yearcon.sport.utils.Sign;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @author itguang
 * @create 2017-12-04 8:57
 **/
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WxController {


    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage;

    @Autowired
    private SysOfficeService sysOfficeService;

    @Autowired
    private SportsWxService sportsWxService;



    @RequestMapping("/authorize")
    public String authorize(HttpServletRequest request) {


        //1.得到请求的 服务器域名
        Map<String,String> map=sportsWxService.getAppid(request);


        //2.通过域名查找 appid 和 appsecret
        String appid = map.get("appid");
        String appsecret = map.get("secret");
        String serverName=map.get("servername");
        //3.构造微信网页授权 url
        String redirectUrl = "http://" + serverName + "/sport/wechat/getOpenid";
        wxMpInMemoryConfigStorage.setAppId(appid);
        wxMpInMemoryConfigStorage.setSecret(appsecret);
        wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        //得到微信网页授权 url
        String url = wxMpService.oauth2buildAuthorizationUrl(redirectUrl, WxConsts.OAUTH2_SCOPE_USER_INFO, null);

        log.info("url={}", url);

        return "redirect:" + url;
        //return Result.success(url);
    }
    @Autowired
    private SportsUserService sportsUserService;

    @RequestMapping("/getOpenid")
    @ResponseBody
    public ModelAndView getOpenid(@RequestParam("code") String code,
                                  HttpServletResponse response, Map<String,Object> map,
                                  HttpServletRequest request) {

        //2.获得accesstoken()
        WxMpUser wxMpUser = null;
        try {
            WxMpOAuth2AccessToken auth2AccessToken = wxMpService.oauth2getAccessToken(code);
            wxMpUser = wxMpService.oauth2getUserInfo(auth2AccessToken, null);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] 授权失败:={}", e.getError().getErrorMsg());
            throw new SportException(ResultEnum.WECHATERROR);
        }


        //获取用户基本信息
        WechatUser wechatUser = new WechatUser();
        BeanUtils.copyProperties(wxMpUser, wechatUser);

        log.info("wxMpUser={}", wxMpUser.toString());
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();

        SportsUsersEntity sportsUsersEntity = sportsUserService.findByVipid(Integer.parseInt(vipid));
        if(sportsUsersEntity==null){
            Map<String,String> appmap=sportsWxService.getAppid(request);
            String webid=appmap.get("webid");
            sportsUsersEntity=sportsUserService.checkvip(wechatUser,vipid,webid);
        }
        map.put("sportsUsersEntity",sportsUsersEntity);

        return new ModelAndView("index",map);
    }
    @Autowired
    private SportsSecretService sportsSecretService;

    /**
     * 获取微信授权api*/
    @RequestMapping(value = "getApi",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getApi(HttpServletRequest request,String url){
        Map<String,String> map=sportsWxService.getAppid(request);
        //String url="http://zongbubeidan.yeksports.com/sport/reg";//request.getRequestURL().toString();
        System.out.println("url:"+url);
        String appid=map.get("appid");
        SportsSecretEntity sportsSecretEntity=sportsSecretService.findOne(appid);
        Map<String, String> ret = new Sign().sign(sportsSecretEntity.getJsapiTicket(), url);
        ret.put("appid",appid);
        return ret;
    }




}
