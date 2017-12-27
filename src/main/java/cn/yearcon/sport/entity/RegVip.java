package cn.yearcon.sport.entity;

import java.io.Serializable;

/**
 * 会员注册实体类
 *
 * @author itguang
 * @create 2017-12-07 11:01
 **/
public class RegVip implements Serializable{

   private String v_mobil;	 //会员手机
   private String v_code;	    //短信验证号码
   private String v_name;	    //真实姓名
   private String v_sex;	    //性别 M 男 W 女
   private String v_birthday;	//会员生日（如20110506）
   private Integer v_store;	    //开卡店仓id
   private String v_passwd;	   //支付密码





    public String getV_mobil() {
        return v_mobil;
    }

    public void setV_mobil(String v_mobil) {
        this.v_mobil = v_mobil;
    }

    public String getV_code() {
        return v_code;
    }

    public void setV_code(String v_code) {
        this.v_code = v_code;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_sex() {
        return v_sex;
    }

    public void setV_sex(String v_sex) {
        this.v_sex = v_sex;
    }

    public String getV_birthday() {
        return v_birthday;
    }

    public void setV_birthday(String v_birthday) {
        this.v_birthday = v_birthday;
    }

    public Integer getV_store() {
        return v_store;
    }

    public void setV_store(Integer v_store) {
        this.v_store = v_store;
    }

    public String getV_passwd() {
        return v_passwd;
    }

    public void setV_passwd(String v_passwd) {
        this.v_passwd = v_passwd;
    }

    @Override
    public String toString() {
        return
                "&v_mobil=" + v_mobil +
                "&v_code=" + v_code +
                "&v_name=" + v_name +
                "&v_sex=" + v_sex +
                "&v_birthday=" + v_birthday +
                "&v_store=" + v_store +
                "&v_passwd=" + v_passwd;
    }
}
