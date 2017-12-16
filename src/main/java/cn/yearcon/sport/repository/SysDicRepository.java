package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SysDictEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysDicRepository extends CrudRepository<SysDictEntity,String> {
     List<SysDictEntity> findByType(String type);
}
