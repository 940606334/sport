package cn.yearcon.sport.modules.entity;

import lombok.Data;

/**
 * @author itguang
 * @create 2017-12-13 9:56
 **/
@Data
public class VipInfo {
   private String vipid;	//会员id
   private String building;//	寄送地址
   private String name;//	真实姓名
   private String sex;	//性别 M 男 W 女
   private String password;	//支付密码



    @Override
    public String toString() {
        return
                "vipid="+ vipid +
                "&building=" + building +
                "&name=" + name +
                "&sex=" + sex +
                "&password=" + password;
    }
}
