package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SysOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author itguang
 * @create 2017-12-04 14:15
 **/
public interface SysOfficeRepository extends JpaRepository<SysOfficeEntity,String> {

    SysOfficeEntity findOneByAddress(String address);
}
