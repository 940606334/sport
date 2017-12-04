package cn.yearcon.sport.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(1,"成功"),
    WECHATERROR(100,"微信网页授权出错,请检查appid和secret"),
    SYSOFFICE_NOT_EXIST(101,"通过域名查找不到相应的 SysOffice"),
    SPORTSWX_NOT_EXIST(102,"通过SysOffice表中code查找不到相应的 SportsWx"),
    


    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
