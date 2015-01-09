package com.riawworks.riaw.erp.model.vo.resources;

public class MenuNodeVo {

	private String id;
	private String text;
	private String state;
	private MenuAttributesVo attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public MenuAttributesVo getAttributes() {
		return attributes;
	}

	public void setAttributes(MenuAttributesVo attributes) {
		this.attributes = attributes;
	}

}
