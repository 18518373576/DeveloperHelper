package com.example.developerandroidx.utils.api;

/**
 * 作者： zjf 2020/6/5 2:57 PM
 * 参考：测试使用接口来自 https://www.wanandroid.com/blog/show/2
 * 描述：接口
 */
public class Api {
    //玩安卓api
    public static final String BASE_URL = "https://www.wanandroid.com/";
    //天气接口
    public static final String BASE_WEATHER_URL = "http://tianqiapi.com/api";
    /**
     * 获取公众号列表
     */
    public static String BLOG_LIST = "wxarticle/chapters/json";

    /**
     * 获取公众号历史文章
     *
     * @param blogId 公众号ID
     * @return url
     */
    public static String getBlogHistory(String blogId, String page) {
        //https://wanandroid.com/wxarticle/list/408/1/json
        return "wxarticle/list/" + blogId + "/" + page + "/json";
    }

    /**
     * 获取一周天气接口
     * http://www.tianqiapi.com/index/doc?version=v9
     *
     * @return 接口url
     */
    public static String getWeatherV1() {
        //http://www.tianqiapi.com/api?version=v1&appid=88474142&appsecret=T1ns0tGh
        //https://tianqiapi.com/api?version=v1&appid=88474142&appsecret=T1ns0tGh
        return BASE_WEATHER_URL + "?version=v1&appid=88474142&appsecret=T1ns0tGh&city=郑州";
    }
}
