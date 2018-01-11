package cn.yearcon.sport.interceptor;

import cn.yearcon.sport.utils.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author itguang
 * @create 2017-12-21 8:22
 **/
public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        Cookie cookie= CookieUtil.get(request,"vipid");
        StringBuffer path=request.getRequestURL();
        if(path.toString().endsWith("/getApi")){
            return true;
        } else if(path.toString().endsWith("/getVipidByMobile")){
            return true;
        }else if(path.toString().endsWith("/getStore")){
            return true;
        }
        if(cookie==null){
            CookieUtil.set(response,"url",path.toString());
            //System.out.println("path="+path);
            response.sendRedirect("/wechat/authorize");
            return false;
        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

}
