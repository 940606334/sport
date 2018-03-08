package cn.yearcon.sport.modules.entity.sport.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Data
@Table(name = "sports_groupbuy", schema = "jeeplus_schema", catalog = "")
public class SportsGroupbuyEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer vipid;
    private String auditstatus;
    private String truename;
    private String mobile;
    private String address;
    private String note;
    private Date addtime;
    private Date audittime;
    private Integer webid;


    @Override
    public String toString() {
        return "SportsGroupbuyEntity{" +
                "id=" + id +
                ", vipid=" + vipid +
                ", auditstatus='" + auditstatus + '\'' +
                ", truename='" + truename + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                ", addtime=" + addtime +
                ", audittime=" + audittime +
                ", webid=" + webid +
                '}';
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
