package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SportsUsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SportsUsersRepository extends CrudRepository<SportsUsersEntity,Integer>{
    SportsUsersEntity findByOpenid(@Param("openid") String openid);
}
