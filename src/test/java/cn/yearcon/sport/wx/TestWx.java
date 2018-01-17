package cn.yearcon.sport.wx;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author itguang
 * @create 2018-01-17 9:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWx {
    @Autowired
    private WxMpService wxService;
    @Autowired
    private WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage;
    @Test
    public void testSendTemplateMsg() throws WxErrorException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        /*TestConfigStorage configStorage = (TestConfigStorage) this.wxService
                .getWxMpConfigStorage();*/
        String appid = "wxe781b118ac32a986";
        String appsecret = "943d048dbe3d6b765143b86522873409";
        wxMpInMemoryConfigStorage.setAppId(appid);
        wxMpInMemoryConfigStorage.setSecret(appsecret);
        wxService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("o7PMW096FQsh_IjhPu1AtUv7IFpE")
                .templateId("a-MfSIu-nc5eNrrnyr8zND1dkbW7cfa7yTj0lvGPzOA").build();
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("first", "\n积分", "#FF00FF"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("remark", "{{phone.DAtA}}\n手机", "#FF00FF"));
        templateMessage.getData().add(new WxMpTemplateData("keyword1", "2333334", "#343434"));
        templateMessage.getData().add(new WxMpTemplateData("keyword2", dateFormat.format(new Date()), "#343434"));
        templateMessage.getData().add(new WxMpTemplateData("keyword3", "130.0", "#343434"));
        templateMessage.setUrl("");
        String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        Assert.assertNotNull(msgId);
        System.out.println(msgId);
    }
}
