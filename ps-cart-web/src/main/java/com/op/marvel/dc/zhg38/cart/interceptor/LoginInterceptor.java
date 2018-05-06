package com.op.marvel.dc.zhg38.cart.interceptor;

import com.op.marvel.dc.zhg38.common.pojo.TbUser;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.threadlocal.UserThreadLoacal;
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
 * @Date Created in 18:34 on 2018/4/27.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TOKEN_KEY}")
    private  String TOKEN_KEY;

    @Autowired
    private ITbUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserThreadLoacal.set(null);
        String token = CookieUtils.getCookieValue(request, TOKEN_KEY);
        if (StringUtils.isEmpty(token)){
            //未登录
            return  true;
        }
        //通过SSO系统查询用户是否登录状态
        ResultInfo result = this.userService.getUserByToken(token);
        if (result.getStatus()!=200){
            //登录超时
            return  true;
        }
        //登录成功
        UserThreadLoacal.set((TbUser) result.getData());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
