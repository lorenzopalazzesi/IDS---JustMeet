package com.unicam.ids.justmeet.IDSJustMeet.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.unicam.ids.justmeet.IDSJustMeet.IdsJustMeetApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IdsJustMeetApplication.class);
	}

}
