package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sys.SysOfficeEntity;
import cn.yearcon.sport.modules.entity.sys.SysUser;
import cn.yearcon.sport.modules.service.sys.SysOfficeService;
import cn.yearcon.sport.modules.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author itguang
 * @create 2017-12-04 14:23
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysOfficeServiceTest {

    @Autowired
    private SysOfficeService sysOfficeService;


    @Test
    public void findOneByAddress(){

        SysOfficeEntity officeEntity = sysOfficeService.findOneByAddress("beijingwx.yeksports.com");
        log.info("entity={}", officeEntity.toString());
    }

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testfindUser() {
        SysOfficeEntity byCode = sysOfficeService.findByCode("190");
        List<SysUser> byOfficeIds = sysUserService.findByOfficeId(byCode.getId());
        SysUser byOfficeId=byOfficeIds.get(0);
        System.out.println("name="+byOfficeId.getName()+",mobile="+byOfficeId.getMobile()+","
        +"loginName="+byOfficeId.getLoginName());
    }
}
