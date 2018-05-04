package com.op.marvel.dc.zhg38.common.source.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 21:16 on 2018/4/19.
 */
public class GlobalExceptionReslover implements HandlerExceptionResolver {
    private  static  final Logger LOGGER=LoggerFactory.getLogger(GlobalExceptionReslover.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {
        //1.打印日志
        LOGGER.error(ExceptionUtils.getStackTrace(ex));
        //2.跳转到错误页面
        ModelAndView mv = new ModelAndView("error/exception");
        mv.addObject("message","您的网络有问题,请稍后重试!");
        return mv;
    }
}
