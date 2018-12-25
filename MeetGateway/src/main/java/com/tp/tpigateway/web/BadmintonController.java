package com.tp.tpigateway.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tp.tpigateway.service.BadmintonService;


@Controller
@RequestMapping(value = "/badminton")
public class BadmintonController {

	private static Logger log = LoggerFactory.getLogger(BadmintonController.class);
	
	@Autowired
	private BadmintonService badmintonService;
	
	@RequestMapping(value = "/insertEvent", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object insertEvent(String name, String time) throws IOException {
		return null;
	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object deleteEvent(String name) throws IOException {
		return null;
	}
	
	@RequestMapping(value = "/queryEvent", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object queryEvent() throws IOException {
		return null;
	}
	
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object addPerson() throws IOException {
		return null;
	}
	
	@RequestMapping(value = "/rmPerson", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object rmPerson() throws IOException {
		return null;
	}
	
	
	
}
