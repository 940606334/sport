package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SportsMsgtemplate;
import cn.yearcon.sport.repository.SportsMsgtemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2018-01-17 14:27
 **/
@Service
public class SportsMsgtemplateService {
    @Autowired
    private SportsMsgtemplateDao sportsMsgtemplateDao;

    public SportsMsgtemplate getByWebid(Integer webid){
        return sportsMsgtemplateDao.findOne(webid);
    }
}
