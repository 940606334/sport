package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SysOfficeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysOfficeRepositoryTest {

    @Autowired
    private SysOfficeRepository sysOfficeRepository;

    @Test
    public void findOneByAddress() {

        SysOfficeEntity oneByAddress = sysOfficeRepository.findOneByAddress("beijingwx.yeksports.com");
        log.info("entity={}", oneByAddress.toString());


    }

}