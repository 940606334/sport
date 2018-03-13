package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.RegVip;
import cn.yearcon.sport.modules.entity.sport.store.StoreIntegral;
import cn.yearcon.sport.modules.entity.sport.user.SportsAuthor;
import cn.yearcon.sport.modules.entity.sport.user.SportsUsersotherEntity;
import cn.yearcon.sport.common.exception.ServiceException;
import cn.yearcon.sport.common.json.JsonResult;
import cn.yearcon.sport.modules.repository.sport.SportsUsersotherRepository;
import cn.yearcon.sport.common.utils.CookieUtil;
import cn.yearcon.sport.modules.service.store.StoreIntegralService;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 会员认证业务层
 *
 * @author itguang
 * @create 2017-12-07 13:58
 **/
@Service
public class SportsUsersotherService {


    @Autowired
    private SportsUsersotherRepository sportsUsersotherRepository;
    @Autowired
    private SportsUserService sportsUserService;

    /**
     * 根据id查询
     * @param vipid
     * @return
     */
    public  SportsUsersotherEntity findById(Integer vipid){
        return sportsUsersotherRepository.findOne(vipid);
    }
    /**注册会员*/
    public void regvip(SportsUsersotherEntity entity,HttpServletResponse response){
        if(entity==null){
            throw new ServiceException("会员信息为空");
        }

        String json=postInterface(entity);
        System.out.println("返回json信息:"+json);

        JsonResult jsonResult=new Gson().fromJson(json, JsonResult.class);

        if(jsonResult.getStatus()==0) {//状态为0注册失败
            throw new ServiceException(jsonResult.getMsg());
        }else if(jsonResult.getStatus()==1){//1注册成功
            String vipid=jsonResult.getMsg();
            CookieUtil.set(response,"vipid",vipid);
            entity.setVipid(Integer.parseInt(vipid));
            entity.setAddtime(new Date());
            entity.setCarttype(cardtype);
            entity.setStorename(getStorename(vipid));
            entity.setVipbirthday(parseDate(entity.getVipbirthday()));
            sportsUsersotherRepository.save(entity);
            //赠送积分
            giveIntegral(entity);



        }

    }

    /**
     * 根据条件判断是否
     * 赠送积分
     * @param entity
     */
    private void giveIntegral(SportsUsersotherEntity entity){
        String storeid=entity.getStoreid().toString();
        StoreIntegral storeIntegral=storeIntegralService.findById(storeid);
        //类型不是开卡送积分
        if(!"1".equals(storeIntegral.getType())){
            return;
        }
        //有期限并且开卡时间不在活动时间范围内
        if("1".equals(storeIntegral.getAvailable())){
            if(entity.getAddtime().getTime()<storeIntegral.getBeginDate().getTime()||
                    entity.getAddtime().getTime()>storeIntegral.getEndDate().getTime()){
                return;
            }
        }
        sportApiService.addIntegral(entity.getVipid().toString(), storeIntegral.getAddIntegral(), "新注册会员送积分");
    }

    @Autowired
    private StoreIntegralService storeIntegralService;
    @Autowired
    private SportApiService sportApiService;
    /**
     * 获取店名
     * @param vipid
     * @return
     */
    public String getStorename(String vipid){
        String json= sportApiService.getVipInfoByid(vipid);
        return (String) JSONPath.read(json, "$.item.storename");
    }

    /**
     *根据id查询
     * @param vipid
     * @return
     */
    public SportsUsersotherEntity get(Integer vipid){

        return sportsUsersotherRepository.findOne(vipid);
    }

    @Value("${interfaceUrl}")
    private String interfaceUrl;
    @Value("${cardtype}")
    private String cardtype;

    /**
     * 注册会员
     * @param entity
     * @return
     */
    public String postInterface(SportsUsersotherEntity entity){
        RegVip regVip=new RegVip();
        String birthday=entity.getVipbirthday();
        regVip.setBirthday(parseDate(birthday));
        regVip.setCheckcode(entity.getCheckcode());
        regVip.setMobile(entity.getMobile());
        regVip.setName(entity.getTruename());
        regVip.setPassword(entity.getVippassword());
        regVip.setStoreid(entity.getStoreid());
        String sex=entity.getVipsex();
        if("1".equals(sex)){
            regVip.setSex("M");
        }else{
            regVip.setSex("W");
        }
        System.out.println(regVip.toString());
        //String url=interfaceUrl+"&app_act=user.reg"+ regVip.toString();
        String json=sportApiService.regVip(regVip.toString());
        return json;
    }

    /**
     * 日期时间转换
     * @param str
     * @return
     */
    public String parseDate(String str){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String birthday="19961111";
        try {
            Date date=simpleDateFormat.parse(str);
            birthday=sdf.format(date);
            //System.out.println(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthday;
    }

    public SportsUsersotherEntity findByMObile(String mobile){
        return sportsUsersotherRepository.findByMobile(mobile);
    }
    /**
     * 根据vipid 保存用户信息
     */
    public void saveByVipid(Integer vipid){
        String json=sportApiService.getVipInfoByid(vipid.toString());
        Integer status=(Integer) JSONPath.read(json,"$.status");
        if(status==1){
            SportsUsersotherEntity entity=new SportsUsersotherEntity();
            entity.setVipid(vipid);
            entity.setStorename((String) JSONPath.read(json,"$.item.storename"));
            entity.setCarttype((String) JSONPath.read(json,"$.item.viptype"));
            entity.setAddtime(new Date());
            entity.setMobile((String) JSONPath.read(json,"$.item.mobil"));
            entity.setOpencarddate((String) JSONPath.read(json,"$.item.opencarddate"));
            entity.setStoreid(Integer.parseInt((String)JSONPath.read(json,"$.item.c_store_id")));
            entity.setTruename((String) JSONPath.read(json,"$.item.vipname"));
            String sex=(String)JSONPath.read(json,"$.item.sex");
            entity.setVipbirthday((String) JSONPath.read(json,"$.item.birthday"));
            if("M".equals(sex)){
                entity.setVipsex("1");
            }else{
                entity.setVipsex("0");
            }
            sportsUsersotherRepository.save(entity);
        }
    }
}
