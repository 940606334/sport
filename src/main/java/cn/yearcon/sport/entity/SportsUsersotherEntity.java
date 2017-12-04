package cn.yearcon.sport.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Table(name = "sports_usersother", schema = "jeeplus_schema", catalog = "")
public class SportsUsersotherEntity {
    private int vipid;
    private String mobile;
    private String vipbirthday;
    private String truename;
    private String carttype;
    private String lastbuy;
    private Integer storeid;
    private String storename;
    private String vipsex;
    private String vipsize;
    private String opencarddate;
    private Timestamp addtime;
    private Timestamp edittime;

    @Id
    @Column(name = "vipid")
    public int getVipid() {
        return vipid;
    }

    public void setVipid(int vipid) {
        this.vipid = vipid;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "vipbirthday")
    public String getVipbirthday() {
        return vipbirthday;
    }

    public void setVipbirthday(String vipbirthday) {
        this.vipbirthday = vipbirthday;
    }

    @Basic
    @Column(name = "truename")
    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    @Basic
    @Column(name = "carttype")
    public String getCarttype() {
        return carttype;
    }

    public void setCarttype(String carttype) {
        this.carttype = carttype;
    }

    @Basic
    @Column(name = "lastbuy")
    public String getLastbuy() {
        return lastbuy;
    }

    public void setLastbuy(String lastbuy) {
        this.lastbuy = lastbuy;
    }

    @Basic
    @Column(name = "storeid")
    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    @Basic
    @Column(name = "storename")
    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    @Basic
    @Column(name = "vipsex")
    public String getVipsex() {
        return vipsex;
    }

    public void setVipsex(String vipsex) {
        this.vipsex = vipsex;
    }

    @Basic
    @Column(name = "vipsize")
    public String getVipsize() {
        return vipsize;
    }

    public void setVipsize(String vipsize) {
        this.vipsize = vipsize;
    }

    @Basic
    @Column(name = "opencarddate")
    public String getOpencarddate() {
        return opencarddate;
    }

    public void setOpencarddate(String opencarddate) {
        this.opencarddate = opencarddate;
    }

    @Basic
    @Column(name = "addtime")
    public Timestamp getAddtime() {
        return addtime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

    @Basic
    @Column(name = "edittime")
    public Timestamp getEdittime() {
        return edittime;
    }

    public void setEdittime(Timestamp edittime) {
        this.edittime = edittime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsUsersotherEntity that = (SportsUsersotherEntity) o;
        return vipid == that.vipid &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(vipbirthday, that.vipbirthday) &&
                Objects.equals(truename, that.truename) &&
                Objects.equals(carttype, that.carttype) &&
                Objects.equals(lastbuy, that.lastbuy) &&
                Objects.equals(storeid, that.storeid) &&
                Objects.equals(storename, that.storename) &&
                Objects.equals(vipsex, that.vipsex) &&
                Objects.equals(vipsize, that.vipsize) &&
                Objects.equals(opencarddate, that.opencarddate) &&
                Objects.equals(addtime, that.addtime) &&
                Objects.equals(edittime, that.edittime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vipid, mobile, vipbirthday, truename, carttype, lastbuy, storeid, storename, vipsex, vipsize, opencarddate, addtime, edittime);
    }
}
