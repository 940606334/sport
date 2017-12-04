package cn.yearcon.sport.handle;

import cn.yearcon.sport.exception.SportException;
import cn.yearcon.sport.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author itguang
 * @create 2017-12-04 15:02
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {


    @ExceptionHandler(value = SportException.class)
    @ResponseBody
    public Result handleSportException(SportException e) {
        log.error("[自定义全局异常:SportException=]:"+e.getCode()+":"+e.getMessage());
        return new Result(e.getCode(), e.getMessage());
    }


}
