package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sys.SysOfficeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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






}
