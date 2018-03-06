package cn.yearcon.sport.modules.entity.sport;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Table(name = "sports_wx", schema = "jeeplus_schema", catalog = "")
@Data
public class SportsWxEntity {


    private Integer webid;
    private String appid;
    private String secret;
    private String payappid;
    private String paysecret;
    private String paymchid;
    private String paykey;
    private Integer storenumber;
    private String webname;
    private String webcode;

    @Id
    @Column(name = "webid")
    public int getWebid() {
        return webid;
    }

    public void setWebid(int webid) {
        this.webid = webid;
    }

    @Basic
    @Column(name = "appid")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Basic
    @Column(name = "secret")
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Basic
    @Column(name = "payappid")
    public String getPayappid() {
        return payappid;
    }

    public void setPayappid(String payappid) {
        this.payappid = payappid;
    }

    @Basic
    @Column(name = "paysecret")
    public String getPaysecret() {
        return paysecret;
    }

    public void setPaysecret(String paysecret) {
        this.paysecret = paysecret;
    }

    @Basic
    @Column(name = "paymchid")
    public String getPaymchid() {
        return paymchid;
    }

    public void setPaymchid(String paymchid) {
        this.paymchid = paymchid;
    }

    @Basic
    @Column(name = "paykey")
    public String getPaykey() {
        return paykey;
    }

    public void setPaykey(String paykey) {
        this.paykey = paykey;
    }

    @Basic
    @Column(name = "storenumber")
    public Integer getStorenumber() {
        return storenumber;
    }

    public void setStorenumber(Integer storenumber) {
        this.storenumber = storenumber;
    }

    @Basic
    @Column(name = "webname")
    public String getWebname() {
        return webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
    }

    @Basic
    @Column(name = "webcode")
    public String getWebcode() {
        return webcode;
    }

    public void setWebcode(String webcode) {
        this.webcode = webcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsWxEntity that = (SportsWxEntity) o;
        return webid == that.webid &&
                Objects.equals(appid, that.appid) &&
                Objects.equals(secret, that.secret) &&
                Objects.equals(payappid, that.payappid) &&
                Objects.equals(paysecret, that.paysecret) &&
                Objects.equals(paymchid, that.paymchid) &&
                Objects.equals(paykey, that.paykey) &&
                Objects.equals(storenumber, that.storenumber) &&
                Objects.equals(webname, that.webname) &&
                Objects.equals(webcode, that.webcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(webid, appid, secret, payappid, paysecret, paymchid, paykey, storenumber, webname, webcode);
    }
}
