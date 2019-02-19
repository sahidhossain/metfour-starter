package com.metafour.starter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Noor Siddique
 * @since 26 Dec 2016
 *
 */
@ControllerAdvice
public class AppControllerAdvice {

	@Value("${basic_cdn_path://cdn.metafour.com/1.2.33/}")
	private String basicCDNPath;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ModelAttribute("basic_cdn_path")
	public String getBasicCDNPath() {
		return basicCDNPath;
	}
}
