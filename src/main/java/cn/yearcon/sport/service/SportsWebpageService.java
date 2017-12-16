package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SportsWebpageEntity;
import cn.yearcon.sport.repository.SportsWebpageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2017-12-12 16:51
 **/
@Service
public class SportsWebpageService {
    @Autowired
    private SportsWebpageRepository sportsWebpageRepository;

    public SportsWebpageEntity findByWebidAndPagecode(Integer webid,String pagecode){
        return sportsWebpageRepository.findByWebidAndPagecode(webid,pagecode);
    }
}
