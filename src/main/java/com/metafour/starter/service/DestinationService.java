package com.metafour.starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.metafour.starter.model.Destination;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@Service
public class DestinationService {
	private List<Destination> destinations = new ArrayList<>();

	@PostConstruct
	private void init() {
		destinations.add(new Destination("DHK", "Dhaka"));
		destinations.add(new Destination("CHT", "Chittagong"));
		destinations.add(new Destination("KHL", "Khulna"));
		destinations.add(new Destination("RSH", "Rajshahi"));
		destinations.add(new Destination("RNG", "Rangpur"));
		destinations.add(new Destination("SLT", "Sylhet"));
		destinations.add(new Destination("BRS", "Barisal"));
		destinations.add(new Destination("COX", "Cox's Bazar"));
		destinations.add(new Destination("BND", "Bandarban"));
	}

	public List<Destination> getAll() {
		return destinations;
	}

	public List<Destination> findBy(String hint) {
		return hint.equals("?") ? destinations : destinations.stream().filter(d -> d.getCode().toLowerCase().contains(hint.toLowerCase()) || d.getDescription().toLowerCase().contains(hint.toLowerCase())).collect(Collectors.toList());
	}
}
