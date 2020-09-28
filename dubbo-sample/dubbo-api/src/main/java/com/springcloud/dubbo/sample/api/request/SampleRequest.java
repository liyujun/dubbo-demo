package com.springcloud.dubbo.sample.api.request;

import java.io.Serializable;
import java.util.Map;

/**
 * 服务接口请求类基类
 * @author manco
 * @since 2020-09-27 10:06:52
 */
public class SampleRequest implements Serializable {

	private static final long serialVersionUID = -1951419648375508632L;

	/**
	 * 为后续业务发展需要，增加请求协议扩展字段
	 * 1、目前主要适用于批量任务处理服务的请求报文
	 */
	private Map<String,Object> expandField;


	public Map<String, Object> getExpandField() {
		return expandField;
	}

	public void setExpandField(Map<String, Object> expandField) {
		this.expandField = expandField;
	}

}
