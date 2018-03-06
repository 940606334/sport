package cn.yearcon.sport.modules.service;

import cn.yearcon.sport.modules.entity.sport.SportsSmscodeEntity;
import cn.yearcon.sport.common.json.JsonResult;
import cn.yearcon.sport.modules.repository.SportsSmscodeRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author itguang
 * @create 2017-12-11 10:21
 **/
@Service
public class SportsSmscodeService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SportsSmscodeRepository sportsSmscodeRepository;
    @Autowired
    private SportApiService sportApiService;

    public SportsSmscodeEntity findByMobile(String mobile){
        return  sportsSmscodeRepository.findOne(mobile);
    }
    public void saveMobile(SportsSmscodeEntity sportsSmscodeEntity){
        //sportsSmscodeEntity.setEdittime(new Date());
        sportsSmscodeRepository.save(sportsSmscodeEntity);
    }

    /**
     * 获取验证码
     * @param mobile
     * @return
     */
    public JsonResult getCheckCode(String mobile){
        SportsSmscodeEntity sportsSmscodeEntity=findByMobile(mobile);
        logger.info("手机短信验证码信息="+sportsSmscodeEntity);
        if(sportsSmscodeEntity==null){
            String json=sportApiService.getCheckcode(mobile);
            Gson gson=new Gson();
            JsonResult jsonResult=gson.fromJson(json,JsonResult.class);
            logger.info("jsonResult="+jsonResult);
            if(jsonResult.getStatus()==1){
                sportsSmscodeEntity=new SportsSmscodeEntity();
                sportsSmscodeEntity.setMobile(mobile);
                sportsSmscodeEntity.setCode(jsonResult.getMsg());
                sportsSmscodeEntity.setAddtime(new Date());
                sportsSmscodeEntity.setDaycount(0);
                logger.info("保存短息信息"+sportsSmscodeEntity);
                saveMobile(sportsSmscodeEntity);
            }
            return jsonResult;
        }else{
            Integer daycount=sportsSmscodeEntity.getDaycount();
            if(daycount==null){
                daycount=0;
            }
            Date edittime=sportsSmscodeEntity.getEdittime();
            if (edittime==null){
                edittime=new Date();
            }
            int i=comparetime(new Date(),edittime);
            //时间相差一天,重新设置次数
            if(i>0){
                daycount=0;
            }
            if (daycount>5){
                return new JsonResult(0,"短信一天只能发送5条");
            }else{
                daycount++;
                String json=sportApiService.getCheckcode(mobile);
                Gson gson=new Gson();
                JsonResult jsonResult=gson.fromJson(json,JsonResult.class);
                if(jsonResult.getStatus()==1){
                    sportsSmscodeEntity.setCode(jsonResult.getMsg());
                    sportsSmscodeEntity.setDaycount(daycount);
                    sportsSmscodeEntity.setEdittime(new Date());//重新设置修改时间
                    logger.info("保存短息信息"+sportsSmscodeEntity);
                    saveMobile(sportsSmscodeEntity);
                }
                return jsonResult;
            }
        }
    }

    /**
     * 比较两个日期相差天数
     * @param date1
     * @param Date2
     * @return
     */
    public int comparetime(Date date1, Date Date2){
        long differ=date1.getTime()-Date2.getTime();
        return (int)(differ/1000/3600/24);
    }
}
