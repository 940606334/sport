package cn.yearcon.sport.modules.repository.sys;

import cn.yearcon.sport.modules.entity.sys.SysUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserDao extends CrudRepository<SysUser,String> {


    List<SysUser> findByOfficeIdAndUserType(@Param("officeId") String officeId,
                                            @Param("userType") String userType);
}
