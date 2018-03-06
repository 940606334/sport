package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sys.SysDictEntity;
import cn.yearcon.sport.modules.repository.SysDicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itguang
 * @create 2017-12-13 13:52
 **/
@Service
public class SysDicService {
    @Autowired
    private SysDicRepository sysDicRepository;
    public List<SysDictEntity> findByType(String type){
        return sysDicRepository.findByType(type);
    }
}
