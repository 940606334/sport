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
@Table(name = "sports_feedback", schema = "jeeplus_schema")
@Data
public class SportsFeedbackEntity {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Date addtime;
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
