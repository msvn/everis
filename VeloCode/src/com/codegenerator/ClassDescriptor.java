package com.codegenerator;

import java.util.ArrayList;

public class ClassDescriptor {

	private String name;
	private String description;
	private ArrayList attributes = new ArrayList();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addAttribute(AttributeDescriptor attribute) {
		attributes.add(attribute);
	}

	public ArrayList getAttributes() {
		return attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}