package cn.yearcon.sport.modules.repository;

import cn.yearcon.sport.modules.entity.sport.SportsWxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author itguang
 * @create 2017-12-04 14:30
 **/
public interface SportsWxRepository extends JpaRepository<SportsWxEntity,Integer> {

    SportsWxEntity findByWebid(Integer webid);
}
