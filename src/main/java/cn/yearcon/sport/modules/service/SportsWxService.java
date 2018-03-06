package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sport.SportsWxEntity;
import cn.yearcon.sport.modules.entity.sys.SysOfficeEntity;
import cn.yearcon.sport.common.json.ResultEnum;
import cn.yearcon.sport.common.exception.SportException;
import cn.yearcon.sport.modules.repository.SportsWxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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


    @Autowired
    private SportsWxService sportsWxService;

    public SportsWxEntity findByWebid(Integer webid){
        SportsWxEntity wxEntity = sportsWxRepository.findByWebid(webid);
        if (wxEntity==null){
            log.error("[通过SysOffice表中code查找不到相应的 SportsWx]");
            throw new SportException(ResultEnum.SPORTSWX_NOT_EXIST);
        }
        return wxEntity;

    }

    public Map<String,String> getAppid(HttpServletRequest request){
        Map<String,String> map=new HashMap<>();
        //1.得到请求的 服务器域名
        String serverName = request.getServerName();
        log.info("serverName={}", serverName);
        SysOfficeEntity officeEntity = sysOfficeService.findOneByAddress(serverName);
        String webid=officeEntity.getCode();
        SportsWxEntity wxEntity = sportsWxService.findByWebid(Integer.parseInt(webid));
        String name=officeEntity.getName();
        //2.通过域名查找 appid 和 appsecret
        String appid = wxEntity.getAppid();
        String appsecret = wxEntity.getSecret();
        map.put("appid",appid);
        map.put("secret",appsecret);
        map.put("servername",serverName);
        map.put("webid",webid);
        map.put("name",name);
        return map;
    }

}
