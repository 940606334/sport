package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sport.SportsWebpageEntity;
import cn.yearcon.sport.modules.repository.SportsWebpageRepository;
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
