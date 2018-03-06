package cn.yearcon.sport.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用程序全局参数
 *
 * @author itguang
 * @create 2017-12-04 9:04
 **/
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class AppParamsConfig {



}
