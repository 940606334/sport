package cn.yearcon.sport.modules.repository;

import cn.yearcon.sport.modules.entity.sys.SysOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author itguang
 * @create 2017-12-04 14:15
 **/
public interface SysOfficeRepository extends JpaRepository<SysOfficeEntity,String> {

    SysOfficeEntity findOneByAddress(String address);


    SysOfficeEntity findOneByCode(String code);
}
