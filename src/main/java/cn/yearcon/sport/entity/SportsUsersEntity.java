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
@Table(name = "sports_users", schema = "jeeplus_schema", catalog = "")
public class SportsUsersEntity {
    private int vipid;
    private Timestamp addtime;
    private Timestamp edittime;
    private Integer brandid;
    private Integer webid;
    private String openid;
    private String unionid;
    private String searchtext;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String headimgurl;
    private String privilege;

    @Id
    @Column(name = "vipid")
    public int getVipid() {
        return vipid;
    }

    public void setVipid(int vipid) {
        this.vipid = vipid;
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

    @Basic
    @Column(name = "brandid")
    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    @Basic
    @Column(name = "webid")
    public Integer getWebid() {
        return webid;
    }

    public void setWebid(Integer webid) {
        this.webid = webid;
    }

    @Basic
    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Basic
    @Column(name = "searchtext")
    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "headimgurl")
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Basic
    @Column(name = "privilege")
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsUsersEntity that = (SportsUsersEntity) o;
        return vipid == that.vipid &&
                Objects.equals(addtime, that.addtime) &&
                Objects.equals(edittime, that.edittime) &&
                Objects.equals(brandid, that.brandid) &&
                Objects.equals(webid, that.webid) &&
                Objects.equals(openid, that.openid) &&
                Objects.equals(unionid, that.unionid) &&
                Objects.equals(searchtext, that.searchtext) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(headimgurl, that.headimgurl) &&
                Objects.equals(privilege, that.privilege);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vipid, addtime, edittime, brandid, webid, openid, unionid, searchtext, nickname, sex, province, city, headimgurl, privilege);
    }
}
