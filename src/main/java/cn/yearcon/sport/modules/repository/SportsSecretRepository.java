package cn.yearcon.sport.modules.repository;

import cn.yearcon.sport.modules.entity.sport.SportsSecretEntity;
import org.springframework.data.repository.CrudRepository;

public interface SportsSecretRepository extends CrudRepository<SportsSecretEntity,String> {
}