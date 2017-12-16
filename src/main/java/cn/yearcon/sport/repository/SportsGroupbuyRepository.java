package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SportsGroupbuyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SportsGroupbuyRepository extends CrudRepository<SportsGroupbuyEntity,Integer>{
    public List<SportsGroupbuyEntity> findByVipidAndAuditstatus(@Param("vipid") Integer vipid,
                                                                @Param("aduitstatus")String auditstatus);
}
