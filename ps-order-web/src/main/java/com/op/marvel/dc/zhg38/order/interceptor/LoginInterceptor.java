package com.op.marvel.dc.zhg38.order.interceptor;

import com.op.marvel.dc.zhg38.common.pojo.TbUser;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.utils.CookieUtils;
import com.op.marvel.dc.zhg38.sso.service.ITbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 14:17 on 2018/5/4.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;
    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private ITbUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.从cookie中获取token信息
        String token = CookieUtils.getCookieValue(request, TOKEN_KEY);
        if(StringUtils.isEmpty(token)){
            //没有渠道token,跳转到sso的登录页面,需要把当前请求的url 作为参数传递给sso,sso 登录成功后则跳转到请求页面
            String requestURL = request.getRequestURL().toString();//获取当前请求的url
            //跳转到登录页面
            response.sendRedirect(SSO_URL+"/page/login?url="+requestURL);
            return  false;
        }
        //取得token,调用sso系统的服务判断用户是否登录
        ResultInfo tokenResult = this.userService.getUserByToken(token);
        //判断用户是否登录,如果未登录,即没有取得用户信息,跳转到sso 的登录页面
        if (tokenResult.getStatus()!=200){
            //获取当前请求的url
            String requestURL = request.getRequestURL().toString();
            //跳转到登录页面
            response.sendRedirect(SSO_URL+"/page/login?url="+requestURL);
            return  false;

        }

        //用户目前登录状态中,将用户信息存入request 中,并且放行
        TbUser user= (TbUser) tokenResult.getData();
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
