package cn.yearcon.sport.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Table(name = "sports_feedback", schema = "jeeplus_schema")
@Data
public class SportsFeedbackEntity {


    @Id
    private Integer id;
    private Timestamp addtime;
    private Timestamp audittime;
    private Integer auditstatus;
    private Integer vipid;
    private String typename1;
    private String typename2;
    private String userask;
    private String askback;
    private Integer webid;
    private Integer feedtype;
    private String usernote;



}
