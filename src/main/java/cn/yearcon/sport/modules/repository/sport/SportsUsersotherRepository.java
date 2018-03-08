package cn.yearcon.sport.modules.repository.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsUsersotherEntity;
import org.springframework.data.repository.CrudRepository;


/**
 * 会员认证数据持久层
 *
 * @author itguang
 * @create 2017-12-07 15:13
 **/

public interface SportsUsersotherRepository extends CrudRepository<SportsUsersotherEntity,Integer> {

    SportsUsersotherEntity findByMobile(String mobile);

}
