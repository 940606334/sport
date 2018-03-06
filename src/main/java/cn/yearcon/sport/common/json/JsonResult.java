package cn.yearcon.sport.common.json;

import lombok.Data;

import java.util.*;

/**
 * 返回json结果
 *
 * @author itguang
 * @create 2017-12-06 13:48
 **/
@Data
public class JsonResult {
    private Integer status;
    private List lists=new ArrayList();
    private String msg;
    private Map item=new HashMap<>();

    public JsonResult(Integer status, Map item) {
        this.status = status;
        this.item = item;
    }

    public JsonResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JsonResult(Integer status) {
        this.status = status;
    }

    public JsonResult(Integer status, List lists) {
        this.status = status;
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", lists=" + lists +
                ", msg='" + msg + '\'' +
                '}';
    }
}
