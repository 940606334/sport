package cn.yearcon.sport.scheduler;

/**
 * 获取微信access_token定时器
 *
 * @author itguang
 * @create 2017-12-10 10:15
 **/

import cn.yearcon.sport.entity.SportsSecretEntity;
import cn.yearcon.sport.json.WxResult;
import cn.yearcon.sport.service.SportsSecretService;
import cn.yearcon.sport.utils.HttpRequestUtils;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Iterator;


/**
 * 全局定时器
 * @author qiqj
 *
 */
@Component
public class Scheduler {

    private final Logger logger = Logger.getRootLogger();

    /*@Resource
    private RedisTokenHelper redisTokenHelper;*/
    /*@Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysOfficeService sysOfficeService;

    @Autowired
    private SportsWxService sportsWxService;*/

    public void getAccessToken(SportsSecretEntity sportsSecretEntity) throws SQLException{
        /*ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);*/
        logger.info("==============开始获取access_token===============");
        //2.通过域名查找 appid 和 appsecret
        String access_token = null;
        String grant_type = "client_credential";
        String AppId= sportsSecretEntity.getAppid();
        String secret=  sportsSecretEntity.getSecret();
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+AppId+"&secret="+secret;
        String json=new HttpRequestUtils().getHttp(url);
        System.out.println("JSON字符串："+json);
        Gson gson=new Gson();
        WxResult wxResult=gson.fromJson(json, WxResult.class);
        access_token=wxResult.getAccess_token();
        sportsSecretEntity.setAccessToken(access_token);
        logger.info("==============结束获取access_token===============");
        logger.info("==============开始获取jsapi_ticket===============");
        url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
        //System.out.println(url);
        json=new HttpRequestUtils().getHttp(url);
        wxResult=gson.fromJson(json, WxResult.class);
        sportsSecretEntity.setJsapiTicket(wxResult.getTicket());
        sportsSecretEntity.setExpiresIn(wxResult.getExpires_in());
        logger.info("==============结束获取jsapi_ticket===============");
        logger.info("==============开始写入access_token===============");
        sportsSecretService.save(sportsSecretEntity);
        logger.info("==============写入access_token成功===============");
    }

    @Autowired
    private SportsSecretService sportsSecretService;
    /**
     * 遍历微信公众号表
     * @throws SQLException
     */
    //@Scheduled(fixedDelay=7180000)
    public void findAllApp(){
        Iterator<SportsSecretEntity> iterator = sportsSecretService.findAll();
        while (iterator.hasNext()){
            try {
                getAccessToken(iterator.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}