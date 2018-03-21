package cn.yearcon.sport.modules.dao;

import cn.yearcon.sport.modules.entity.sport.user.SportsGivereward;
import cn.yearcon.sport.modules.repository.sport.SportsGiverewardDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ayong
 * @create 2018-03-20 18:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {
    @Autowired
    private SportsGiverewardDao sportsGiverewardDao;

    @Test
    public void test(){
        SportsGivereward sportsGivereward=new SportsGivereward();
        sportsGivereward.setRemark("测试");
        sportsGivereward.setGiveintegral(9);
        sportsGivereward.setVipid(123);
        sportsGivereward.setGuideid("123123123412bcsc");
        sportsGivereward.setWebid(110);
        sportsGiverewardDao.save(sportsGivereward);
    }

}
