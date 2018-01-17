package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SportsMsgtemplate;
import cn.yearcon.sport.entity.SportsUsersEntity;
import cn.yearcon.sport.entity.TicketMsg;
import cn.yearcon.sport.json.JsonResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 微信消息模板
 *
 * @author itguang
 * @create 2018-01-17 11:08
 **/
@Service
public class WxMsgService {
    @Autowired
    private WxMpService wxService;
    @Autowired
    private WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage;
    @Autowired
    private SportsWxService sportsWxService;
    @Autowired
    private SportsUserService sportsUserService;
    @Autowired
    private SportsMsgtemplateService sportsMsgtemplateService;

    /**
     * 发送店址电子小票
     * @param ticketMsg
     * @param request
     * @return
     */
    public JsonResult sendTicketMsg(TicketMsg ticketMsg, HttpServletRequest request){

        Map<String, String> map = sportsWxService.getAppid(request);
        String appid = map.get("appid");
        String appsecret = map.get("secret");
        String webid=map.get("webid");
        SportsUsersEntity sportsUsersEntity=sportsUserService.findByVipid(Integer.parseInt(ticketMsg.getVipid()));
        if(sportsUsersEntity==null){
            return new JsonResult(0,"会员信息为空");
        }
        String openid=sportsUsersEntity.getOpenid();
        SportsMsgtemplate sportsMsgtemplate=sportsMsgtemplateService.getByWebid(Integer.parseInt(webid));
        if(sportsMsgtemplate==null){
            return new JsonResult(0,"请配置电子小票模板");
        }
        wxMpInMemoryConfigStorage.setAppId(appid);
        wxMpInMemoryConfigStorage.setSecret(appsecret);
        wxService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openid)
                .templateId(sportsMsgtemplate.getTemplateid()).build();
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("first", sportsMsgtemplate.getFirst(), "#343434"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("remark", ticketMsg.toString()+sportsMsgtemplate.getRemark(), "#343434"));
        templateMessage.getData().add(new WxMpTemplateData("keyword1", ticketMsg.getKeyword1(), "#343434"));//交易单号
        templateMessage.getData().add(new WxMpTemplateData("keyword2", ticketMsg.getKeyword2(), "#343434"));//交易时间
        templateMessage.getData().add(new WxMpTemplateData("keyword3", ticketMsg.getKeyword3(), "#343434"));//交易金额
        templateMessage.setUrl(sportsMsgtemplate.getRedirecturl());
        JsonResult jsonResult=null;
        try {
            String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            jsonResult=new JsonResult(1,msgId);
        }catch (WxErrorException e){
            jsonResult=new JsonResult(0,e.getMessage());
        }
        return jsonResult;
    }
}
