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
@Table(name = "sports_usersother", schema = "jeeplus_schema", catalog = "")
public class SportsUsersotherEntity {
    @Id
    private Integer vipid;
    private String mobile;
    private String vipbirthday;
    private String truename;
    private String carttype;
    private String lastbuy;
    private Integer storeid;
    private String storename;
    private String vipsex;
    private String vipsize;
    private String opencarddate;
    private Date addtime;
    private Date edittime;

    @Transient
    private String vippassword;
    @Transient
    private String checkcode;


}
