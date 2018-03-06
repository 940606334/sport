package cn.yearcon.sport.modules.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 会员注册实体类
 *
 * @author itguang
 * @create 2017-12-07 11:01
 **/
@Data
public class RegVip implements Serializable{

   private String mobile;	 //会员手机
   private String checkcode;	    //短信验证号码
   private String name;	    //真实姓名
   private String sex;	    //性别 M 男 W 女
   private String  birthday;	//会员生日（如20110506）
   private Integer storeid;	    //开卡店仓id
   private String password;	   //支付密码


    @Override
    public String toString() {
        return  "mobile="+mobile+
                "&checkcode="+checkcode+
                "&name="+name +
                "&sex="+sex +
                "&birthday="+birthday+
                "&storeid="+storeid+
                "&password="+ password;
    }
}
