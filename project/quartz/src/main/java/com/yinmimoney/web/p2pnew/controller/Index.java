package com.yinmimoney.web.p2pnew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Index {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		return "Hello World!";
	}
}