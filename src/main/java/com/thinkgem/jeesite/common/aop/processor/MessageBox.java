package com.thinkgem.jeesite.common.aop.processor;

import java.io.Serializable;
import java.util.Map;

public class MessageBox implements Serializable {

	private static final long serialVersionUID = -770558557256525814L;
	private String code;
	private String message;
	private String newToken;
	private Map<String, String> result;
    private Integer counter;
    private String revision;
	public MessageBox() {
	 	 
	}

 
	
	public MessageBox(String code, String message,Map<String, String> result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getResult() {
		return result;
	}

	public void setResult(Map<String, String> result) {
		this.result = result;
	}

	 
	public static final MessageBox buildForCounter(String code, String message) {
		 
		return new MessageBox(code, message,0);
	}
	public static final MessageBox build(String code, String message,Map<String, String> result) {
		return new MessageBox(code, message,result);
	}
    
	public MessageBox(String code, String message, Integer counter) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getNewToken() {
		return newToken;
	}

	public void setNewToken(String newToken) {
		this.newToken = newToken;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}


	 
	
}
