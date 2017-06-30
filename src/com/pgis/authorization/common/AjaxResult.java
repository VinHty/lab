package com.pgis.authorization.common;

import java.io.Serializable;

public class AjaxResult implements Serializable {
	
	public static final Integer AJAX_STATUS_CODE_SUCCESS = 0;
    public static final Integer AJAX_STATUS_CODE_WARN = 1;
    public static final Integer AJAX_STATUS_CODE_ERROR = 2;

    private Integer statusCode;
    private String message;

    public AjaxResult() {
    	super();
    }
    
    public static AjaxResult success(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setMessage("操作成功");
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_SUCCESS);
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_ERROR);
        return ajaxResult;
    }

    public static AjaxResult Warn(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_WARN);
        return ajaxResult;
    }

    public AjaxResult(Integer statusCode, String message){
    	super();
        this.statusCode = statusCode;
        this.message = message;
    }
    
    public Integer getStatusCode(){
        return statusCode;
    }
    public AjaxResult setStatusCode(Integer statusCode){
        this.statusCode = statusCode;
        return this;
    }
    
    public String getMessage(){
        return message;
    }
    public AjaxResult setMessage(String message){
        this.message = message;
        return this;
    }
}
