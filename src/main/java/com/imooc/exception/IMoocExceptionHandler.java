package com.imooc.exception;

import com.imooc.pojo.IMoocJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class IMoocExceptionHandler {

	public static final String IMOOC_ERROR_VIEW = "error";

	@Autowired
	private MappingJackson2HttpMessageConverter jsonConverter;
/*	@ExceptionHandler(value = Exception.class)
   public Object errorHandler(HttpServletRequest reqest,
   		HttpServletResponse response, Exception e) throws Exception {

    	e.printStackTrace();

	   ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
       mav.addObject("url", reqest.getRequestURL());
        mav.setViewName(IMOOC_ERROR_VIEW);
        return mav;
   }*/
	
	@ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
							   HttpServletResponse response, Exception e) throws Exception {
    	
    	e.printStackTrace();
    	
    	if (isAjax(reqest)) {
			IMoocJSONResult result = IMoocJSONResult.errorException(e.getMessage());
			jsonConverter.write(result, MediaType.APPLICATION_JSON,new ServletServerHttpResponse(response));
			return null;
    	} else {
    		ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(IMOOC_ERROR_VIEW);
            return mav;
    	}
    }
	
	/**
	 * 
	 * @Title: IMoocExceptionHandler.java
	 * @Package com.imooc.exception
	 * @Description: 判断是否是ajax请求
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 * 
	 * @author leechenxiang
	 * @date 2017年12月3日 下午1:40:39
	 * @version V1.0
	 */
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null  
					&& "XMLHttpRequest".equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
}
