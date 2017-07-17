package com.hjl.core.exception.resolver;

import com.hjl.core.exception.BizException;
import com.hjl.core.message.bean.JsonDataResult;
import com.hjl.core.message.enums.RespCodeEnum;
import com.hjl.base.utils.GsonUtil;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by hjl on 2016/12/20.
 */

public class BizExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

		logger.error(ex.getMessage(), ex);

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		String viewName = determineViewName(ex, request);
		if (viewName != null) {

			Object obj = method.getAnnotation(ResponseBody.class);
			if (obj != null) {
				JsonDataResult<Object> result = new JsonDataResult<Object>();
				try {
					if (ex instanceof BizException) {
						Object data = ((BizException) ex).getData();
						String code = ((BizException) ex).getCode();
						String msg = ((BizException) ex).getMsg();
						result.setData(data);
						result.setCode(code == null ? RespCodeEnum.RC_0001.code() : code);
						result.setMsg(msg == null ? RespCodeEnum.RC_0001.description() :msg);
					}else {
						result.init(RespCodeEnum.RC_0001.code() , RespCodeEnum.RC_0001.description());
					}
					//设置返回类型为json
					response.setContentType("application/json;charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					PrintWriter out = response.getWriter();
					out.print(GsonUtil.toJson(result));
					out.flush();
					out.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				return null;
			} else {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			}
		} else {
			return null;
		}
	}

}
