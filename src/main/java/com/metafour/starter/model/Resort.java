package com.metafour.starter.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
public class Resort {

	@NotEmpty
	private String code;
	@NotEmpty
	private String description;
	@NotEmpty
	private String destination;
	private String[] departurePorts;
	private String[] arrivalPorts;

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

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String[] getDeparturePorts() {
		return departurePorts;
	}

	public void setDeparturePorts(String[] departurePorts) {
		this.departurePorts = departurePorts;
	}

	public String[] getArrivalPorts() {
		return arrivalPorts;
	}

	public void setArrivalPorts(String[] arrivalPorts) {
		this.arrivalPorts = arrivalPorts;
	}

}
