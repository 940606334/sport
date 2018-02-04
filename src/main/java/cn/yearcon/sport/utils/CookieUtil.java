package cn.yearcon.sport.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ayong
 * @create 2017-12-04 10:40
 **/
public class CookieUtil {

    /**
     * 设置Cookie
     * @param response
     * @param name cookie的名称
     * @param value cookie值
     */
    public static void set(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        //cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static void set(HttpServletResponse response, String name, String value,Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    /**
     * 获取Cookie
     * @param request
     * @param name cookie名称
     * @return
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> map = readCookieAsMap(request);
        if(map.containsKey(name)){
            return map.get(name);
        }
        return null;
    }

    /**
     * 将客户端发来的cookie封装成 Map
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieAsMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> map = new HashMap<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie);
            }
        }
        return map;
    }



}
