package cn.yearcon.sport.modules.entity.sport.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-09 16:16
 **/
@Entity
@Table(name = "sports_extrainfo", schema = "sports", catalog = "")
@Data
public class SportsExtrainfo {
    @Id
    private int vipid; //会员id
    private String email; //邮箱
    private String weblog; //微博
    private String qq; //qq
    @Column(name = "sport_type")
    private String sportType;
    @Column(name = "activity_type")
    private String activityType;
    @Column(name = "gift_type")
    private String giftType;
    @Column(name = "have_baby")
    private String haveBaby;
    private String size;//鞋码



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsExtrainfo that = (SportsExtrainfo) o;
        return vipid == that.vipid &&
                Objects.equals(email, that.email) &&
                Objects.equals(weblog, that.weblog) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(sportType, that.sportType) &&
                Objects.equals(activityType, that.activityType) &&
                Objects.equals(giftType, that.giftType) &&
                Objects.equals(haveBaby, that.haveBaby);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vipid, email, weblog, qq, sportType, activityType, giftType, haveBaby);
    }
}
