package cn.yearcon.sport.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回json结果
 *
 * @author itguang
 * @create 2017-12-06 13:48
 **/
public class JsonResult {
    private Integer status;//0.失败,1.成功
    private String info;//提示信息
    /*private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }*/

    private LinkedHashMap<String, Object> lists = new LinkedHashMap();//封装json的map

    public LinkedHashMap<String, Object> getLists() {
        return lists;
    }

    public void setLists(LinkedHashMap<String, Object> lists) {
        this.lists = lists;
    }
    public void put(String key, Object value){//向json中添加属性，在js中访问，请调用data.map.key
        lists.put(key, value);
    }

    public void remove(String key){
        lists.remove(key);
    }
    public JsonResult() {
        this.status = 0;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
