package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsGivereward;
import cn.yearcon.sport.modules.repository.sport.SportsGiverewardDao;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2018-01-19 8:43
 **/
@Service
public class SportsGiverewardService {
    private SportsGiverewardDao sportsGiverewardDao;

    /**
     * 保存
     * @param sportsGivereward
     */
    public SportsGivereward save(SportsGivereward sportsGivereward){
        SportsGivereward givereward = sportsGiverewardDao.save(sportsGivereward);
        return givereward;
    }
}
