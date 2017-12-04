package cn.yearcon.sport.entity;

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
@Table(name = "sports_webpage", schema = "jeeplus_schema", catalog = "")
public class SportsWebpageEntity {
    private int id;
    private String pagecode;
    private String imgurl;
    private String pagecontent;
    private String pageurl;
    private Integer webid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pagecode")
    public String getPagecode() {
        return pagecode;
    }

    public void setPagecode(String pagecode) {
        this.pagecode = pagecode;
    }

    @Basic
    @Column(name = "imgurl")
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Basic
    @Column(name = "pagecontent")
    public String getPagecontent() {
        return pagecontent;
    }

    public void setPagecontent(String pagecontent) {
        this.pagecontent = pagecontent;
    }

    @Basic
    @Column(name = "pageurl")
    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    @Basic
    @Column(name = "webid")
    public Integer getWebid() {
        return webid;
    }

    public void setWebid(Integer webid) {
        this.webid = webid;
    }

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
