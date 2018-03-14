package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsFeedbackEntity;
import cn.yearcon.sport.modules.entity.sys.SysOfficeEntity;
import cn.yearcon.sport.modules.entity.sys.SysUser;
import cn.yearcon.sport.modules.repository.sport.SportsFeedbackRepository;
import cn.yearcon.sport.modules.service.sys.SysOfficeService;
import cn.yearcon.sport.modules.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itguang
 * @create 2017-12-13 14:56
 **/
@Service
public class SportsFeedbackService {
    @Autowired
    private SportsFeedbackRepository sportsFeedbackRepository;

    @Autowired
    private SysOfficeService sysOfficeService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SportApiService sportApiService;

    public void saveFeedback(SportsFeedbackEntity sportsFeedbackEntity){
        SysOfficeEntity officeEntity = sysOfficeService.findByCode(sportsFeedbackEntity.getWebid().toString());
        List<SysUser> sysUserList= sysUserService.findByOfficeId(officeEntity.getId());
        for (SysUser sysUser:sysUserList){
            String mobile=sysUser.getMobile();
            if (mobile!=null&&mobile!="") {
                sportApiService.sendSmsMsg(mobile, "你有新的会员心声要处理,详情请登录'意尔康体育微会员管理平台'");
            }
        }

        sportsFeedbackRepository.save(sportsFeedbackEntity);
    }
}
