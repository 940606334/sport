package cn.yearcon.sport.common.config;

import cn.yearcon.sport.common.interceptor.MyInterceptor1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义拦截器配置
 *
 * @author itguang
 * @create 2017-12-21 8:24
 **/
@Configuration
public class MyWebAppConfigurer  extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor1())
                .addPathPatterns("/user/**")
                .addPathPatterns("/index")
                .excludePathPatterns("/reg")
                .excludePathPatterns("/regvip")
                .excludePathPatterns("/login")
                .excludePathPatterns("/toUpload")
                .excludePathPatterns("/upload")
                .excludePathPatterns("/getCode")
                .excludePathPatterns("/getVipidByMobile")
                .excludePathPatterns("/getStore")
                .excludePathPatterns("/checkmobile")
                .excludePathPatterns("/wechat/**")
                .excludePathPatterns("/getShoparea");
        //registry.addInterceptor(new UploadInterceptor()).addPathPatterns("/toUpload");
        super.addInterceptors(registry);
    }
   /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/**");
    }*/
}
