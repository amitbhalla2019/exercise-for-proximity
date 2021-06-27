package com.abhalla.demo.model;

import java.util.List;

public class BasicEntity {

	private String id;
	private String name;
	private List<String> tags;
	
	public BasicEntity() {
	}
	
	public BasicEntity(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}