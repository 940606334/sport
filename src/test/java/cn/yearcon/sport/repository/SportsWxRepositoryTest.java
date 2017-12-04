package cn.yearcon.sport.repository;

import cn.yearcon.sport.entity.SportsWxEntity;
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
public class SportsWxRepositoryTest {

    @Autowired
    private SportsWxRepository sportsWxRepository;

    @Test
    public void findByWebid() {

        SportsWxEntity wxEntity = sportsWxRepository.findByWebid(669);
        log.info("wxEntity={}",wxEntity);

    }
}