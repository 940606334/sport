package cn.yearcon.sport.exception;

import cn.yearcon.sport.enums.ResultEnum;
import lombok.Data;

/**
 * @author itguang
 * @create 2017-12-04 14:56
 **/
@Data
public class SportException extends RuntimeException {

    private Integer code;

    public SportException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
