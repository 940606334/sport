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
@Table(name = "sports_secret", schema = "jeeplus_schema", catalog = "")
public class SportsSecretEntity {
    @Id
    private String appid;
    private Date addtime;
    private Date edittime;
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "expires_in")
    private Integer expiresIn;
    private String secret;
    @Column(name = "jsapi_ticket")
    private String jsapiTicket;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsSecretEntity that = (SportsSecretEntity) o;
        return Objects.equals(appid, that.appid) &&
                Objects.equals(addtime, that.addtime) &&
                Objects.equals(edittime, that.edittime) &&
                Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(expiresIn, that.expiresIn) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(appid, addtime, edittime, accessToken, expiresIn, secret);
    }
}
