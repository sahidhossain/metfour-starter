package com.metafour.starter.model;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
public class Destination {
	private String code;
	private String description;

	public Destination(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
