package cn.yearcon.sport.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

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
}
