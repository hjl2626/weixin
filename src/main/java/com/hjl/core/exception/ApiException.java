package com.hjl.core.exception;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hjl
 * Date: 2017-07-18
 * Time: 16:54
 */
public class ApiException extends BizException{
    
    
    private static final long serialVersionUID = -1197500071336952124L;
    
    public ApiException(String code, String msg) {
        super(code, msg);
    }
    
    public ApiException(String code, String msg , Object data) {
        super(code, msg ,data);
    }
}
