package cn.yearcon.sport;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author itguang
 * @create 2017-12-15 11:08
 **/
public class ServletInitializer  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SportApplication.class);
    }
}
