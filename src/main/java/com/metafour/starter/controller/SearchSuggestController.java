package com.metafour.starter.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metafour.starter.service.DestinationService;
import com.metafour.starter.service.PersonService;
import com.metafour.starter.service.ResortService;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@RestController
@RequestMapping("/search")
public class SearchSuggestController {
	
	@Autowired DestinationService destinationService;
	@Autowired ResortService resortService;
	@Autowired PersonService personService;

	@RequestMapping("/destination/{hint}")
	public Collection<Map<String, String>> searchDestination(@PathVariable String hint) {
		return destinationService.findBy(hint).stream().map(it -> {
			Map<String, String> item = new HashMap<>();
			item.put("key", it.getCode());
			item.put("value", it.getDescription());
			return item;
		}).collect(Collectors.toList());
	}

	@RequestMapping("/city/{hint}")
	public Collection<Map<String, String>> searchCity(@PathVariable String hint) {
		return searchDestination(hint);
	}

	@RequestMapping("/resort/{hint}")
	public Collection<Map<String, String>> searchResort(@PathVariable String hint) {
		return resortService.find(hint).stream().map(it -> {
			Map<String, String> item = new HashMap<>();
			item.put("key", it.getCode());
			item.put("value", it.getDescription());
			return item;
		}).collect(Collectors.toList());
	}

	@RequestMapping("/person/{hint}")
	public Collection<Map<String, String>> searchPerson(@PathVariable String hint) {
		return personService.find(hint).stream().map(it -> {
			Map<String, String> item = new HashMap<>();
			item.put("key", it.getId());
			item.put("value", it.getName());
			return item;
		}).collect(Collectors.toList());
	}
}
