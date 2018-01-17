package cn.yearcon.sport.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author itguang
 * @create 2018-01-17 14:22
 **/
@Entity
@Data
@Table(name = "sports_msgtemplate", schema = "sports", catalog = "")
public class SportsMsgtemplate {
    @Id
    private int webid;
    private String name;
    private String templateid;
    private String first;
    private String remark;
    private String redirecturl;
    private String usable;

    @Override
    public String toString() {
        return "SportsMsgtemplate{" +
                "webid=" + webid +
                ", name='" + name + '\'' +
                ", templateid='" + templateid + '\'' +
                ", first='" + first + '\'' +
                ", remark='" + remark + '\'' +
                ", redirecturl='" + redirecturl + '\'' +
                ", usable='" + usable + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsMsgtemplate that = (SportsMsgtemplate) o;
        return webid == that.webid &&
                Objects.equals(name, that.name) &&
                Objects.equals(templateid, that.templateid) &&
                Objects.equals(first, that.first) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(redirecturl, that.redirecturl) &&
                Objects.equals(usable, that.usable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(webid, name, templateid, first, remark, redirecturl, usable);
    }
}
