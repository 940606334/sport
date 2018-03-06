package cn.yearcon.sport.util;

import cn.yearcon.sport.common.utils.UrlUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author itguang
 * @create 2018-01-12 14:20
 **/
public class TestDate {
    @Test
    public void test(){
        String str="1968年11月9日";
        System.out.println(str);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date date=simpleDateFormat.parse(str);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            String brithday=sdf.format(date);
            System.out.println(brithday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2() throws ParseException {
        String str="2018-1-16";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date Date2=simpleDateFormat.parse(str);
        Date date1=new Date();
        long differ=date1.getTime()-Date2.getTime();
        System.out.println(differ/1000/3600/24);
    }

    @Test
    public void test3() throws Exception {
        String str="http://mp.weixin.qq.com/mp/homepage?__biz=MzU0MTQ5NjgxMg==&amp;hid=1&amp;sn=89b9dbb634c612ce0fd94f73034f16e4#wechat_redirect";
        //String urlEnCode = URLEncoder.encode(str,"UTF-8");
        //str=UrlUtil.getURLEncoderString(str);
        //str=UrlUtil.getURLDecoderString(str);
        str=str.replaceAll("&amp;","&");
        //str= URLDecoder.decode(str,"GBK");
        System.out.println(str);
    }
}
