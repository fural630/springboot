package com.xhz.web.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@RequestMapping("{module}/{url}.html")
	public String toPage(@PathVariable String module, @PathVariable String url) {
		logger.debug("jump page to {}", module + "/" + url + ".html");
		return module + "/" + url;
	}

}
