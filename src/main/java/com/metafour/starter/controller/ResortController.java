package com.metafour.starter.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.metafour.starter.model.Resort;
import com.metafour.starter.service.PortService;
import com.metafour.starter.service.ResortService;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@Controller
@RequestMapping("/resort")
public class ResortController {
	
	@Autowired PortService portService;
	@Autowired ResortService resortService;

	@RequestMapping
	public String newScreen(final ModelMap model) {
		model.addAttribute("resort", new Resort());
		model.addAttribute("ports", portService.getAll());
		return "resort/resort";
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addNewResort(@Valid Resort resort, BindingResult binding, final ModelMap model) throws MetafourStarterException, BindException {
		Map<String, String> result = new HashMap<>();
		if (binding.hasErrors()) throw new BindException(binding);

		if (resortService.getByCode(resort.getCode()) == null)
			resortService.addResort(resort);
		else
			resortService.updateResort(resort);

		result.put("status", "success");
		result.put("redirect", "/" + resort.getCode());
		return result;
	}

	@RequestMapping("/{code}")
	public String updateScreen(@PathVariable String code, final ModelMap model) throws MetafourStarterException {
		model.addAttribute("resort", resortService.getByCode(code));
		model.addAttribute("ports", portService.getAll());
		return "resort/resort";
	}
}
