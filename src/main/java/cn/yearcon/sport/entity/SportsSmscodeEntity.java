package cn.yearcon.sport.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Data
@Table(name = "sports_smscode", schema = "jeeplus_schema", catalog = "")
public class SportsSmscodeEntity {

    @Id
    private String mobile;
    private String code;
    private Date addtime;
    private Date edittime;
    private Integer daycount;
    private Integer action;




}
