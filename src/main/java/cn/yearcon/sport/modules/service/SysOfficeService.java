package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sys.SysOfficeEntity;
import cn.yearcon.sport.common.json.ResultEnum;
import cn.yearcon.sport.common.exception.SportException;
import cn.yearcon.sport.modules.repository.SysOfficeRepository;
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

    public String findNameByCode(String code){
        SysOfficeEntity sysOfficeEntity=sysOfficeRepository.findOneByCode(code);
        if(sysOfficeEntity==null){
            return "";
        }else{
            return sysOfficeEntity.getName();
        }
    }


}
