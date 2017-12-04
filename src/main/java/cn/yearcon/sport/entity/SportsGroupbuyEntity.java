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
@Table(name = "sports_groupbuy", schema = "jeeplus_schema", catalog = "")
public class SportsGroupbuyEntity {
    private int id;
    private Integer vipid;
    private String auditstatus;
    private String truename;
    private String mobile;
    private String address;
    private String note;
    private Timestamp addtime;
    private Timestamp audittime;
    private Integer webid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vipid")
    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    @Basic
    @Column(name = "auditstatus")
    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus;
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
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
    @Column(name = "audittime")
    public Timestamp getAudittime() {
        return audittime;
    }

    public void setAudittime(Timestamp audittime) {
        this.audittime = audittime;
    }

    @Basic
    @Column(name = "webid")
    public Integer getWebid() {
        return webid;
    }

    public void setWebid(Integer webid) {
        this.webid = webid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsGroupbuyEntity that = (SportsGroupbuyEntity) o;
        return id == that.id &&
                Objects.equals(vipid, that.vipid) &&
                Objects.equals(auditstatus, that.auditstatus) &&
                Objects.equals(truename, that.truename) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(address, that.address) &&
                Objects.equals(note, that.note) &&
                Objects.equals(addtime, that.addtime) &&
                Objects.equals(audittime, that.audittime) &&
                Objects.equals(webid, that.webid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, vipid, auditstatus, truename, mobile, address, note, addtime, audittime, webid);
    }
}
