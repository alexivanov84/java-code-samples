package org.nomoretea.mediaService.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({"org.nomoretea.mediaService.web"})
public class WebConfig extends WebMvcConfigurerAdapter {

	public WebConfig()
	{
		super();
	}

}