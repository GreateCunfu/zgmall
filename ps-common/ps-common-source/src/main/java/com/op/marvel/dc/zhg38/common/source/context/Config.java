package com.op.marvel.dc.zhg38.common.source.context;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:系统配置类，配置了系统用到参数
 * @Date Created in 21:45 on 2018/5/2.
 */
public class Config {
    /**
     * 系统语言环境，默认为中文zh
     */
    public static final String LANGUAGE = "zh";

    /**
     * 系统国家环境，默认为中国CN
     */
    public static final String COUNTRY = "CN";

    /**
     * ISO 8859-1
     */
    public static final String CHARSET_8859="iso8859-1";

    /**
     * utf8
     */
    public static final String CHARSET_UTF_8 = "utf-8";



    /**
     * 搜索相关
     */

    public static final String  QUERY_KEY= "query";
    public static final String  TOTAL_PAGES_KEY= "totalPages";
    public static final String  ITEM_LIST_KEY= "itemList";
    public static final String  PAGE_KEY= "page";

    /**
     * search 页面视图名称
     */
    public static final String VIEW_SEARCH = "search";

    /**
     * 公开权限，用户无需登录即可使用
     */
    public static final String ANONYMOUS_ACTIONS = "anonymousActions";

    /**
     * 系统参数配置文件名称
     */
    public static final String SYSCONFIG = "sysParams";

    /**
     * session中存放登录用户的key名称
     */
    public static final String ACTIVEUSER_KEY = "activeUser";

    /**
     * 配置文件中系统基础url的key名称
     */
    public static final String BASEURL_KEY = "baseurl";

    /**
     * 配置文件中系统管理url的key名称
     */
    public static final String SYSMANAGERURL_KEY = "sysmanagerurl";

    /**
     * 配置文件中系统配置url的key名称
     */
    public static final String SYSCONFIGURL_KEY = "sysconfigurl";

    /**
     * 配置文件中加密密钥的key名称
     */
    public static final String DESKEY_KEY = "deskey";

    /**
     * 系统管理及系统配置平台接入key参数
     */
    public static final String LOGINKEY = "loginkeyString";

    /**
     * 配置文件中系统版本名称的key名称
     */
    public static final String VERSION_NAME_KEY = "version_name";

    /**
     * 配置文件中系统版本号的key名称
     */
    public static final String VERSION_NUMBER_KEY = "version_number";

    /**
     * 配置文件中系统版本发布时间的key名称
     */
    public static final String VERSION_DATE_KEY = "version_date";

    /**
     * 系统提示信息ResultInfo对象key
     */
    public static final String RESULTINFO_KEY = "resultInfo";





    /**
     * 一般错误提示页面,该路径需要匹配页面前后缀
     */
    public static final String ERROR_PAGE = "/error";







}
