package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.common.utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 运动伯俊API
 *
 * @author itguang
 * @create 2018-01-03 14:29
 **/
@Service
public class SportApiService {

    @Value("${interfaceUrl}")
    private String interfaceUrl;

    /**
     * 根据vipid 获取用户信息
     * @param vipid
     * @return
     */
    public String getVipInfoByid(String vipid){
        String url=interfaceUrl+"user.detail?vipid="+vipid;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 根据手机号获取vipid
     * @param mobile
     * @return
     */
    public String getVipidByMobile(String mobile){
        String url=interfaceUrl+"user.id?mobile="+mobile;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 根据手机号获取验证码
     * @param mobile
     * @return
     */
    public String getCheckcode(String mobile){
        String url=interfaceUrl+"code.get?mobile="+mobile;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 获取门店列表
     * @param webid
     * @param coordinate
     * @return
     */
    public String getStoreInfo(String webid,String coordinate){
        String url=interfaceUrl+"store.list?webid="+webid+"&coordinate="+coordinate;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 获取积分列表
     * @param vipid
     * @return
     */
    public String getIntegralList(String vipid){
        String url=interfaceUrl+"integral.list?vipid="+vipid;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 获取剩余积分
     * @param vipid
     * @return
     */
    public String getIntegral(String vipid){
        String url=interfaceUrl+"integral.num?vipid="+vipid;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 获取优惠券列表
     * @param vipid
     * @return
     */
    public String getCouponList(String vipid){
        String url=interfaceUrl+"voucher.list?vipid="+vipid;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 获取账单
     * @param vipid
     * @return
     */
    public String getBillList(String vipid){
        String url=interfaceUrl+"retail.list?vipid="+vipid;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 注册会员信息
     * @param regStr
     * @return
     */
    public String regVip(String regStr){
        String url=interfaceUrl+"user.reg?"+regStr;
        return new HttpRequestUtils().getHttp(url);
    }
    /**
     * 修改会员信息
     */
    public String updateVip(String vipStr){
        String url=interfaceUrl+"user.edit?"+vipStr;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 修改会员认证
     * @param vipid
     * @return
     */
    public String authorizeVip(String vipid){
        String url=interfaceUrl+"vip.authorize?"+vipid;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 增加积分
     * @param vipid
     * @param integral
     * @param remark
     * @return
     */
    public String addIntegral(String vipid,Integer integral,String remark){
        String url=interfaceUrl+"integral.add?vipid="+vipid+"&integral="+integral+"&remark="+remark;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 花费积分
     * @param vipid
     * @param integral
     * @param remark
     * @param docno
     * @return
     */
    public String costIntegral(String vipid,Integer integral,String remark,String docno){
        String url=interfaceUrl+"integral.cost?vipid="+vipid+"&integral="+integral+"&remark="+remark+"&docno="+docno;
        return new HttpRequestUtils().getHttp(url);
    }

    /**
     * 发送短信
     * @param mobile
     * @param content
     * @return
     */
    public String sendSmsMsg(String mobile,String content){
        String url=interfaceUrl+"sms.send?mobile="+mobile+"&content="+content;
        return new HttpRequestUtils().getHttp(url);
    }
}
