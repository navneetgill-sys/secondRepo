package com.shp.test.api.product;

import java.util.HashMap;
import java.util.Map;

public class Album {
	private Integer userId;
	private String title;
	private Integer id;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Integer getUserId() {
	return userId;
	}

	public void setUserId(Integer userId) {
	this.userId = userId;
	}

	public String getTitle() {
	return title;
	}

	public void setTitle(String title) {
	this.title = title;
	}

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}


}
