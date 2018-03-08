package cn.yearcon.sport.modules.repository.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsUsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SportsUsersRepository extends CrudRepository<SportsUsersEntity,Integer>{
    SportsUsersEntity findByOpenid(@Param("openid") String openid);
}
