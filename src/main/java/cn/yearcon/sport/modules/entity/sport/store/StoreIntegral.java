package cn.yearcon.sport.modules.entity.sport.store;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-08 16:10
 **/
@Entity
@Table(name = "sports_store_integral", schema = "sports", catalog = "")
@Data
public class StoreIntegral {
    @Id
    private String id;
    private String webid;
    private String name;
    private String available;
    @Column(name = "add_integral")
    private Integer addIntegral;
    @Column(name = "begin_date")
    private Timestamp beginDate;
    @Column(name = "end_date")
    private Timestamp endDate;
    private String type;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreIntegral that = (StoreIntegral) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(webid, that.webid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(available, that.available) &&
                Objects.equals(addIntegral, that.addIntegral) &&
                Objects.equals(beginDate, that.beginDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, webid, name, available, addIntegral, beginDate, endDate, type);
    }
}
