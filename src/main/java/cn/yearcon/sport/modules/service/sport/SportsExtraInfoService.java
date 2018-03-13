package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsExtrainfo;
import cn.yearcon.sport.modules.repository.sport.SportsExtraInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-13 14:16
 **/
@Service
public class SportsExtraInfoService {

    @Autowired
    private SportsExtraInfoDao sportsExtraInfoDao;

    public SportsExtrainfo findById(Integer vipid){
        return sportsExtraInfoDao.findOne(vipid);
    }

    public void save(SportsExtrainfo sportsExtrainfo){
        sportsExtraInfoDao.save(sportsExtrainfo);
    }

}
