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
@Table(name = "sports_usersnote", schema = "jeeplus_schema", catalog = "")
public class SportsUsersnoteEntity {
    private String id;
    private Integer vipid;
    private String vipnote;
    private Timestamp addtime;
    private Integer webid;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @Column(name = "vipnote")
    public String getVipnote() {
        return vipnote;
    }

    public void setVipnote(String vipnote) {
        this.vipnote = vipnote;
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
        SportsUsersnoteEntity that = (SportsUsersnoteEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(vipid, that.vipid) &&
                Objects.equals(vipnote, that.vipnote) &&
                Objects.equals(addtime, that.addtime) &&
                Objects.equals(webid, that.webid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, vipid, vipnote, addtime, webid);
    }
}
