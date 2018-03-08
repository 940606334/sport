package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sport.SportsWxEntity;
import cn.yearcon.sport.modules.repository.sport.SportsWxRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author itguang
 * @create 2017-12-04 14:36
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SportsWxServiceTest {

    @Autowired
    private SportsWxRepository sportsWxRepository;

    @Test
    public void findByWebid(){

        SportsWxEntity wxEntity = sportsWxRepository.findByWebid(669);
        log.info("wxEntity={}",wxEntity);

    }


}
