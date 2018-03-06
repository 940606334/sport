package cn.yearcon.sport.modules.entity.sport;

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
@Table(name = "sports_page", schema = "jeeplus_schema", catalog = "")
public class SportsPageEntity {
    private int id;
    private String name;
    private String url;
    private String code;
    private Boolean isseturl;
    private Boolean issetcontent;
    private String note;
    private Boolean issetimg;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "isseturl")
    public Boolean getIsseturl() {
        return isseturl;
    }

    public void setIsseturl(Boolean isseturl) {
        this.isseturl = isseturl;
    }

    @Basic
    @Column(name = "issetcontent")
    public Boolean getIssetcontent() {
        return issetcontent;
    }

    public void setIssetcontent(Boolean issetcontent) {
        this.issetcontent = issetcontent;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "issetimg")
    public Boolean getIssetimg() {
        return issetimg;
    }

    public void setIssetimg(Boolean issetimg) {
        this.issetimg = issetimg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsPageEntity that = (SportsPageEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(code, that.code) &&
                Objects.equals(isseturl, that.isseturl) &&
                Objects.equals(issetcontent, that.issetcontent) &&
                Objects.equals(note, that.note) &&
                Objects.equals(issetimg, that.issetimg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, url, code, isseturl, issetcontent, note, issetimg);
    }
}
