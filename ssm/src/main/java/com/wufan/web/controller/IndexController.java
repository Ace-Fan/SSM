package com.wufan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	/**
	 * Ìø×ªÊ×Ò³
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
