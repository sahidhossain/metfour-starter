package com.metafour.starter.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metafour.starter.exception.MetafourStarterException;
import com.metafour.starter.model.Person;
import com.metafour.starter.service.DestinationService;
import com.metafour.starter.service.PersonService;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@Controller
@RequestMapping("/person")
public class PersonController {

	private final Map<String, String> COURSES = new HashMap<>();
	@Autowired PersonService personService;
	@Autowired DestinationService destinationService;
	
	@PostConstruct
	private void init() {
		COURSES.put("1", "Course 1");
		COURSES.put("2", "Course 2");
		COURSES.put("3", "Course 3");
		COURSES.put("4", "Course 4");
		COURSES.put("5", "Course 5");
		COURSES.put("6", "Course 6");
		COURSES.put("7", "Course 7");
		COURSES.put("8", "Course 8");
		COURSES.put("9", "Course 9");
		COURSES.put("10", "Course 10");
	}

	@RequestMapping
	public String newScreen(final ModelMap model) throws MetafourStarterException {
		return updateScreen(null, model);
	}

	@RequestMapping("/{id}")
	public String updateScreen(@PathVariable String id, final ModelMap model) throws MetafourStarterException {
		model.addAttribute("person", id == null ? new Person() : personService.getById(id));
		model.addAttribute("courses", COURSES);
		model.addAttribute("degrees", Arrays.asList("SSC", "HSC", "Graduation", "Post Graduation"));
		return "person/person";
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addNewResort(@Valid Person person, BindingResult binding, final ModelMap model) throws MetafourStarterException, BindException {
		Map<String, String> result = new HashMap<>();
		if (binding.hasErrors()) throw new BindException(binding);

		if (person.getId() == null || personService.getById(person.getId()) == null)
			personService.addPerson(person);
		else
			personService.updatePerson(person);

		result.put("status", "success");
		result.put("redirect", "/" + person.getId());
		return result;
	}
}
