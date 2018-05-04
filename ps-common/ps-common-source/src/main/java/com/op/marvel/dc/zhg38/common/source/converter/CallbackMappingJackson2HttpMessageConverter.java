package com.op.marvel.dc.zhg38.common.source.converter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.op.marvel.dc.zhg38.source.utils.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 21:25 on 2018/4/26.
 */
public class CallbackMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    private String callbackName;
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
       //从threadLocal中获取当前的reqeust 对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String callbackParam = request.getParameter(callbackName);
        if (StringUtils.isEmpty(callbackParam)){
            super.writeInternal(object, type, outputMessage);

        }else{
            //支持jsonp
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            try {
                String result=callbackParam+"("+JsonUtil.obj2Json(object)+")";
                IOUtils.write(result,outputMessage.getBody(),encoding.getJavaName());
            } catch (IOException e) {
                throw  new HttpMessageNotWritableException("Could not write JSON L"+e.getMessage(),e);
            }
        }

    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }
}
