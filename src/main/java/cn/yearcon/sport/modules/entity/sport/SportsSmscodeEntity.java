package cn.yearcon.sport.modules.entity.sport;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
