package cn.yearcon.sport.modules.entity.sport.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author itguang
 * @create 2018-01-18 10:58
 **/
@Entity
@Data
@Table(name = "sports_givereward", schema = "sports", catalog = "")
public class SportsGivereward {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String guideid;
    private Integer webid;
    private Integer vipid;
    private Integer giveintegral;
    private String type;
    private String explain;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsGivereward that = (SportsGivereward) o;
        return id == that.id &&
                Objects.equals(guideid, that.guideid) &&
                Objects.equals(webid, that.webid) &&
                Objects.equals(vipid, that.vipid) &&
                Objects.equals(giveintegral, that.giveintegral) &&
                Objects.equals(type, that.type) &&
                Objects.equals(explain, that.explain);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, guideid, webid, vipid, giveintegral, type, explain);
    }
}
