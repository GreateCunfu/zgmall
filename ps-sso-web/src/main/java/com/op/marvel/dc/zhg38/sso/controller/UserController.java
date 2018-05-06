package com.op.marvel.dc.zhg38.sso.controller;

import com.op.marvel.dc.zhg38.common.pojo.TbUser;
import com.op.marvel.dc.zhg38.common.source.bean.vo.ResultInfo;
import com.op.marvel.dc.zhg38.common.source.utils.CookieUtils;
import com.op.marvel.dc.zhg38.sso.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:42 on 2018/5/3.
 */
@Controller
@RequestMapping("user")
public class UserController {


    @Value("${TOKEN_KEY}")
    private  String TOKEN_KEY;
    @Autowired
    private ITbUserService iTbUserService;

    @RequestMapping("check/{param}/{type}")
    @ResponseBody
    public ResultInfo checkUserData(@PathVariable String param, @PathVariable Integer type) {
        ResultInfo result = iTbUserService.checkData(param, type);
        return result;
    }

    @RequestMapping(value="register", method=RequestMethod.POST)
    @ResponseBody
    public ResultInfo regitster(TbUser user) {
        ResultInfo result = iTbUserService.register(user);
        return result;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public @ResponseBody
    ResultInfo login(String userName, String password, HttpServletRequest request, HttpServletResponse response){

        ResultInfo result=this.iTbUserService.login(userName,password);
        if (result.getStatus()==200){
            //把token 写入cookie中
            CookieUtils.setCookie(request,response,TOKEN_KEY,result.getData().toString());
        }
        return result;
    }
  /* @RequestMapping(value = "token/{token}",method = RequestMethod.GET,
   //指定返回响应数据的content-type
           produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody Object getUserByToken(@PathVariable("token") String token,String callback){
       TaotaoResult res = this.iTbUserService.getUserByToken(token);
       //判断是否为jsonp 请求
       if (StringUtils.isNotEmpty(callback)){
           return  callback+"("+JsonUtil.obj2Json(res)+")";
       }
       return JsonUtil.obj2Json(res);
   }*/


    //此方法 只适合 spring 4.0 以上
 /*@RequestMapping(value = "token/{token}",method = RequestMethod.GET,
   //指定返回响应数据的content-type
           produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody Object getUserByToken(@PathVariable("token") String token,String callback){
       TaotaoResult res = this.iTbUserService.getUserByToken(token);
       //判断是否为jsonp 请求
       if (StringUtils.isNotEmpty(callback)){
           MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(res);
           //设置回调
           mappingJacksonValue.setJsonpFunction(callback);
           return  mappingJacksonValue;
       }
       return res;
   }*/
    @RequestMapping(value = "token/{token}",method = RequestMethod.GET,
            //指定返回响应数据的content-type
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody ResultInfo getUserByToken(@PathVariable("token") String token){
        ResultInfo res = this.iTbUserService.getUserByToken(token);

        return res;
    }

}

