package cn.yearcon.sport.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author itguang
 * @create 2017-12-04 8:36
 **/
@Entity
@Data
@Table(name = "sports_webpage", schema = "jeeplus_schema", catalog = "")
public class SportsWebpageEntity {
    @Id
    private Integer id;
    private String pagecode;
    private String imgurl;
    private String pagecontent;
    private String pageurl;
    private Integer webid;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsWebpageEntity that = (SportsWebpageEntity) o;
        return id == that.id &&
                Objects.equals(pagecode, that.pagecode) &&
                Objects.equals(imgurl, that.imgurl) &&
                Objects.equals(pagecontent, that.pagecontent) &&
                Objects.equals(pageurl, that.pageurl) &&
                Objects.equals(webid, that.webid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pagecode, imgurl, pagecontent, pageurl, webid);
    }
}
