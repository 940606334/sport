package cn.yearcon.sport.modules.entity.sport;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Data
@Table(name = "sports_users", schema = "jeeplus_schema", catalog = "")
public class SportsUsersEntity {
    @Id
    private int vipid;
    private Date addtime;
    private Date edittime;
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
    @Transient
    private String integral;
    @Transient
    private String coupon;
}
