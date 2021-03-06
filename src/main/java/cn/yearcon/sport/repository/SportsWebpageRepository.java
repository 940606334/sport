package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SportsWebpageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author itguang
 * @create 2017-12-12 16:48
 **/
public interface SportsWebpageRepository extends CrudRepository<SportsWebpageEntity,Integer>{
    public SportsWebpageEntity findByWebidAndPagecode(@Param("webid") Integer webid, @Param("pagecode") String pagecode);
}
