package cn.yearcon.sport.entity;

/**
 * @author itguang
 * @create 2017-12-13 9:56
 **/
public class VipInfo {
   private String vipid;	//会员id
   private String v_building;//	寄送地址
   private String v_name;//	真实姓名
   private String v_sex;	//性别 M 男 W 女
   private String v_passwd;	//支付密码

    public String getVipid() {
        return vipid;
    }

    public void setVipid(String vipid) {
        this.vipid = vipid;
    }

    public String getV_building() {
        return v_building;
    }

    public void setV_building(String v_building) {
        this.v_building = v_building;
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

    public String getV_passwd() {
        return v_passwd;
    }

    public void setV_passwd(String v_passwd) {
        this.v_passwd = v_passwd;
    }

    @Override
    public String toString() {
        return
                "&vipid="+ vipid +
                "&v_building=" + v_building +
                "&v_name=" + v_name +
                "&v_sex=" + v_sex +
                "&v_passwd=" + v_passwd ;
    }
}
