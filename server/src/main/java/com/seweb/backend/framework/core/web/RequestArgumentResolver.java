package com.seweb.backend.framework.core.web;

import java.util.Date;

import com.seweb.backend.domain.Client;
import com.seweb.backend.domain.Staff;
import com.seweb.backend.domain.type.UserType;
import com.seweb.backend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;
import com.seweb.backend.service.*;
import com.seweb.backend.framework.utils.date.DateUtil;

public class RequestArgumentResolver implements HandlerMethodArgumentResolver 
{
	@Autowired
	private StaffService staffService;

	@Autowired
	private ClientService clientService;
	
    @Override
    public boolean supportsParameter(MethodParameter methodParameter)
    {
    	System.out.println("请求是否为Request类型："+methodParameter.getParameterType().equals(Request.class));
    	//如果controller的形参为Request对象，则返回true，表示需要处理该参数分解，调用下面的resolveArgument处理
        return methodParameter.getParameterType().equals(Request.class);
    }
 
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception 
    {
    	Request request = new Request();

    	//通过请求中的参数username设置request的user&userType
    	String username = webRequest.getParameter("username");

		/* MODIFIED BEGIN*/

		if(username != null) {
			Staff staff = staffService.getUserByUsername(username);
			if (staff == null) {
				Client client = clientService.getUserByUsername(username);
				if (client == null) {
					throw new Exception("Username not exist");
				}
				request.setUserType(UserType.CLIENT);
				request.setUser(client);
			} else if (staff.getAdmin() == 1) {
				request.setUserType(UserType.ADMIN);
				request.setUser(staff);
			} else {
				request.setUserType(UserType.STAFF);
				request.setUser(staff);
			}
		}


    	/* END */

    	String[] descriptions = webRequest.getDescription(true).split(";");
    	String uri = descriptions[0].split("=")[1];
    	String client = descriptions[1].split("=")[1];
    	request.setUri(uri);
    	request.setClient(client);
    	
    	String dateTime = DateUtil.formatTime(new Date());
    	request.setDateTime(dateTime);
    	
        String paramsString = webRequest.getParameter("params");
        JSONObject params = JSONObject.parseObject(paramsString);
        
        request.setParams(params);

        return request;
    }

}