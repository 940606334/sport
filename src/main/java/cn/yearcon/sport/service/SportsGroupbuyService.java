package cn.yearcon.sport.service;

import cn.yearcon.sport.entity.SportsGroupbuyEntity;
import cn.yearcon.sport.repository.SportsGroupbuyRepository;
import cn.yearcon.sport.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 团购申请业务层
 *
 * @author itguang
 * @create 2017-12-12 9:02
 **/
@Service
public class SportsGroupbuyService {
    @Autowired
    private SportsGroupbuyRepository sportsGroupbuyRepository;
    public List<SportsGroupbuyEntity> findbyVipid(HttpServletRequest request){
        Cookie cookie=CookieUtil.get(request,"vipid");
        String vipid=cookie.getValue();
        return sportsGroupbuyRepository.findByVipidAndAuditstatus(Integer.parseInt(vipid),"0");
    }
    public SportsGroupbuyEntity findById(Integer id){
        return sportsGroupbuyRepository.findOne(id);
    }
    public void save(SportsGroupbuyEntity sportsGroupbuyEntity){
        sportsGroupbuyRepository.save(sportsGroupbuyEntity);
    }
}
