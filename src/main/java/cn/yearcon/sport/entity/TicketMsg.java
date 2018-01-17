package cn.yearcon.sport.entity;

import lombok.Data;
import org.junit.Test;

/**
 * @author itguang
 * @create 2018-01-17 13:57
 **/
@Data
public class TicketMsg {

    private String vipid;//会员id
    private String keyword1;//订单编号
    private String keyword2;//消费时间
    private String keyword3;//消费金额
    private String addintegral;//增加积分
    private String costintegral;//抵现积分
    private String totalintegral;//累计积分

    @Override
    public String toString() {
        return "增加积分:" + addintegral + "\n" +
                "抵现积分:" + costintegral + "\n" +
                "累计积分:" + totalintegral + "\n";
    }
}
