package cn.yearcon.sport.modules.entity.sport.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-09 9:05
 **/
@Entity
@Table(name = "sports_author", schema = "sports", catalog = "")
@Data
public class SportsAuthor {
    @Id
    private int vipid;
    @Column(name = "first_author")
    private String firstAuthor; //认证信息
    @Column(name = "add_info")
    private String addInfo; //完善资料


    public SportsAuthor() {
    }

    public SportsAuthor(int vipid, String firstAuthor) {
        this.vipid = vipid;
        this.firstAuthor = firstAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsAuthor that = (SportsAuthor) o;
        return vipid == that.vipid &&
                Objects.equals(firstAuthor, that.firstAuthor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vipid, firstAuthor);
    }
}
