package cn.yearcon.sport.modules.service.store;

import cn.yearcon.sport.modules.entity.sport.store.StoreIntegral;
import cn.yearcon.sport.modules.repository.store.StoreIntegralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-08 16:27
 **/
@Service
public class StoreIntegralService {

    @Autowired
    private StoreIntegralDao storeIntegralDao;

    public StoreIntegral findById(String id){
       return storeIntegralDao.findOne(id);
    }
}
