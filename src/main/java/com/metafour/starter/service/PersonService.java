package com.metafour.starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.metafour.starter.exception.MetafourStarterException;
import com.metafour.starter.model.Person;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@Service
public class PersonService {
	List<Person> persons = new ArrayList<>();

	public void addPerson(Person person) throws MetafourStarterException {
		person.setId(String.valueOf(new Random().nextLong()));
		persons.add(person);
	}

	public void updatePerson(Person person) throws MetafourStarterException {
		List<Person> rsts = persons.stream().filter(r -> r.getId().equals(person.getId())).collect(Collectors.toList());
		if (rsts.isEmpty())
			throw new MetafourStarterException("No Person found with ID '" + person.getId() + "' for update!");
		persons.remove(rsts.get(0));
		persons.add(person);
	}

	public void deletePerson(Person person) throws MetafourStarterException {
		List<Person> rsts = persons.stream().filter(r -> r.getId().equals(person.getId())).collect(Collectors.toList());
		if (rsts.isEmpty())
			throw new MetafourStarterException("No Person found with ID '" + person.getId() + "' for delete!");
		persons.remove(rsts.get(0));
	}

	public List<Person> find(String hint) {
		return hint.equals("?") ? persons : persons.stream().filter(d -> d.getId().toLowerCase().contains(hint.toLowerCase()) || d.getName().toLowerCase().contains(hint.toLowerCase())).collect(Collectors.toList());
	}

	public Person getById(String id) throws MetafourStarterException {
		List<Person> rsts = persons.stream().filter(r -> r.getId().equals(id)).collect(Collectors.toList());
		if (rsts.size() != 1)
			return null;
		return rsts.get(0);
	}
}
