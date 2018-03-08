package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.SportsSecretEntity;
import cn.yearcon.sport.common.exception.ServiceException;
import cn.yearcon.sport.modules.repository.sport.SportsSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;


/**
 * 微信公众号业务层
 *
 * @author itguang
 * @create 2017-12-10 13:36
 **/
@Service
public class SportsSecretService {
    @Autowired
    private SportsSecretRepository sportsSecretRepositoty;

    public Iterator<SportsSecretEntity> findAll(){
        return sportsSecretRepositoty.findAll().iterator();
    }

    public void save(SportsSecretEntity sportsSecretEntity){
        if(sportsSecretEntity==null){
            throw new ServiceException("要保存的对象不能为空");
        }
        sportsSecretEntity.setEdittime(new Date());
        sportsSecretRepositoty.save(sportsSecretEntity);
    }
    public SportsSecretEntity findOne(String appid){
        return sportsSecretRepositoty.findOne(appid);
    }
}
