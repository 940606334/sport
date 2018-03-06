package cn.yearcon.sport.modules.repository;

import cn.yearcon.sport.modules.entity.sys.SysDictEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysDicRepository extends CrudRepository<SysDictEntity,String> {
     List<SysDictEntity> findByType(String type);
}
