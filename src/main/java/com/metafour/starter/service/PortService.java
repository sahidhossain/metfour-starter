package com.metafour.starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.metafour.starter.model.Port;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@Service
public class PortService {
	private List<Port> ports = new ArrayList<>();

	@PostConstruct
	private void init() {
		ports.add(new Port("DHKA", "Dhaka Airport"));
		ports.add(new Port("CHTA", "Chittagong Airport"));
		ports.add(new Port("KHLA", "Khulna Airport"));
		ports.add(new Port("RSHA", "Rajshahi Airport"));
		ports.add(new Port("SDPA", "Saidpur Airport"));
		ports.add(new Port("SLTA", "Sylhet Airport"));
		ports.add(new Port("BRSA", "Barisal Airport"));
		ports.add(new Port("COXA", "Cox's Bazar Airport"));
	}

	public List<Port> getAll() {
		return ports;
	}

	public List<Port> findBy(String hint) {
		return hint.equals("?") ? ports : ports.stream().filter(d -> d.getCode().toLowerCase().contains(hint.toLowerCase()) || d.getDescription().toLowerCase().contains(hint.toLowerCase())).collect(Collectors.toList());
	}
}
