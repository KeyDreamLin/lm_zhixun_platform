package com.lm.common.ex.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lm.common.ex.ErrorHandler;
import com.lm.common.r.R;
import com.lm.entity.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应结果统一处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 这里返回true就做调用beforeBodyWrite函数处理数据，false否则不处理
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       log.info("统一数据处理msg--->{}",body);
        //判断如果是抛出异常的时候，则使用R.error
       if (body instanceof ErrorHandler){

           // 1、如果返回的结果是一个异常的结果，就把异常返回的结构数据倒腾到R.error里面即可
           ErrorHandler errorHandler = (ErrorHandler)body;

           User u = new User();
           User sss = (User) R.sss(u);
           sss.getImageName();

           User user = R.s11(u);
            user.getUserId();

           return R.error(errorHandler.getStatus(),errorHandler.getMessage());
       }
       else if (body instanceof String){
           // 2、因为springmvc数据转换器对String是有特殊处理 StringHttpMessageConverter
           try {
               ObjectMapper objectMapper = new ObjectMapper();
               R r = R.success(body);
               return objectMapper.writeValueAsString(r);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
       }
       return R.success(body);
    }
}
