package cn.yearcon.sport.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 微信用户信息
 *
 * @author itguang
 * @create 2017-12-04 14:04
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WechatUser {

    private String openId;
    private String nickname;
    private String sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;
    private Long subscribeTime;
    private String unionId;
    private Integer sexId;
    private String remark;
    private Integer groupId;


}
