package cn.yearcon.sport.modules.repository;

import cn.yearcon.sport.modules.entity.sport.SportsWxEntity;
import cn.yearcon.sport.modules.repository.sport.SportsWxRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SportsWxRepositoryTest {

    @Autowired
    private SportsWxRepository sportsWxRepository;

    @Test
    public void findByWebid() {

        SportsWxEntity wxEntity = sportsWxRepository.findByWebid(669);
        log.info("wxEntity={}",wxEntity);

    }
}