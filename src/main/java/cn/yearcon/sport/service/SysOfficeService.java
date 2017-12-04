package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SysOfficeEntity;
import cn.yearcon.sport.enums.ResultEnum;
import cn.yearcon.sport.exception.SportException;
import cn.yearcon.sport.repository.SysOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2017-12-04 14:21
 **/
@Service
public class SysOfficeService {

    @Autowired
    private SysOfficeRepository sysOfficeRepository;


    public SysOfficeEntity findOneByAddress(String address){
        SysOfficeEntity officeEntity = sysOfficeRepository.findOneByAddress(address);
        if (officeEntity==null){
            throw new SportException(ResultEnum.SYSOFFICE_NOT_EXIST);
        }
        return officeEntity;
    }



}
