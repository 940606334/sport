package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SportsSmscodeEntity;
import cn.yearcon.sport.repository.SportsSmscodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author itguang
 * @create 2017-12-11 10:21
 **/
@Service
public class SportsSmscodeService {
    @Autowired
    private SportsSmscodeRepository sportsSmscodeRepository;

    public SportsSmscodeEntity findByMobile(String mobile){
        return  sportsSmscodeRepository.findOne(mobile);
    }
    public void saveMobile(SportsSmscodeEntity sportsSmscodeEntity){
        sportsSmscodeEntity.setEdittime(new Date());
        sportsSmscodeRepository.save(sportsSmscodeEntity);
    }
}
