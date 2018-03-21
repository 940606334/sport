package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.SportsPageEntity;
import cn.yearcon.sport.modules.repository.sport.SportsPageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-14 10:39
 **/
@Service
public class SportsPageService {
    @Autowired
    private SportsPageDao sportsPageDao;

    public SportsPageEntity findOne(Integer id){
        return sportsPageDao.findOne(id);
    }
}
