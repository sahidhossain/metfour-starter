package com.metafour.starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.metafour.starter.exception.MetafourStarterException;
import com.metafour.starter.model.Resort;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@Service
public class ResortService {
	List<Resort> resorts = new ArrayList<>();

	public void addResort(Resort resort) throws MetafourStarterException {
		if (resorts.stream().filter(r -> r.getCode().equals(resort.getCode())).count() > 0)
			throw new MetafourStarterException("Resort code '" + resort.getCode() + "' already exists!");
		resorts.add(resort);
	}

	public void updateResort(Resort resort) throws MetafourStarterException {
		List<Resort> rsts = resorts.stream().filter(r -> r.getCode().equals(resort.getCode())).collect(Collectors.toList());
		if (rsts.isEmpty())
			throw new MetafourStarterException("No Resort found with code '" + resort.getCode() + "' for update!");
		resorts.remove(rsts.get(0));
		resorts.add(resort);
	}

	public void deleteResort(Resort resort) throws MetafourStarterException {
		List<Resort> rsts = resorts.stream().filter(r -> r.getCode().equals(resort.getCode())).collect(Collectors.toList());
		if (rsts.isEmpty())
			throw new MetafourStarterException("No Resort found with code '" + resort.getCode() + "' for deletion!");
		resorts.remove(rsts.get(0));
	}

	public List<Resort> find(String hint) {
		return hint.equals("?") ? resorts : resorts.stream().filter(d -> d.getCode().toLowerCase().contains(hint.toLowerCase()) || d.getDescription().toLowerCase().contains(hint.toLowerCase())).collect(Collectors.toList());
	}

	public Resort getByCode(String code) throws MetafourStarterException {
		List<Resort> rsts = resorts.stream().filter(r -> r.getCode().equals(code)).collect(Collectors.toList());
		if (rsts.size() != 1)
			return null;
		return rsts.get(0);
	}
}
