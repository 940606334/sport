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
@Table(name = "sports_smscode", schema = "jeeplus_schema", catalog = "")
public class SportsSmscodeEntity {
    private int id;
    private String mobile;
    private String code;
    private Timestamp addtime;
    private Timestamp edittime;
    private Integer daycount;
    private Integer action;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "daycount")
    public Integer getDaycount() {
        return daycount;
    }

    public void setDaycount(Integer daycount) {
        this.daycount = daycount;
    }

    @Basic
    @Column(name = "action")
    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsSmscodeEntity that = (SportsSmscodeEntity) o;
        return id == that.id &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(code, that.code) &&
                Objects.equals(addtime, that.addtime) &&
                Objects.equals(edittime, that.edittime) &&
                Objects.equals(daycount, that.daycount) &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mobile, code, addtime, edittime, daycount, action);
    }
}
