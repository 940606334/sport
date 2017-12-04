package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SportsWxEntity;
import cn.yearcon.sport.enums.ResultEnum;
import cn.yearcon.sport.exception.SportException;
import cn.yearcon.sport.repository.SportsWxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2017-12-04 14:35
 **/
@Service
@Slf4j
public class SportsWxService {

    @Autowired
    private SportsWxRepository sportsWxRepository;

    @Autowired
    private SysOfficeService sysOfficeService;

    public SportsWxEntity findByWebid(Integer webid){
        SportsWxEntity wxEntity = sportsWxRepository.findByWebid(webid);
        if (wxEntity==null){
            log.error("[通过SysOffice表中code查找不到相应的 SportsWx]");
            throw new SportException(ResultEnum.SPORTSWX_NOT_EXIST);
        }
        return wxEntity;

    }


}
