package cn.yearcon.sport.web;

import cn.yearcon.sport.entity.TicketMsg;
import cn.yearcon.sport.json.JsonResult;
import cn.yearcon.sport.service.WxMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信消息模板
 *
 * @author itguang
 * @create 2018-01-17 11:02
 **/
@Controller
@RequestMapping(value = "wxmsg")
public class WxMsgController {

    @Autowired
    private WxMsgService wxMsgService;

    @RequestMapping(value = "sendTicketMsg")
    @ResponseBody
    public JsonResult getTicketMsg(TicketMsg ticketMsg,HttpServletRequest request){
        return wxMsgService.sendTicketMsg(ticketMsg,request);
    }
}
