package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// Downloaded content config
	// String downloadedContentUri =
	// DemoLineApiApplication.downloadedContentDir.toUri().toASCIIString();
	// log.info("downloaded dir: {}", downloadedContentUri);
	// registry.addResourceHandler("/downloaded/**").addResourceLocations(downloadedContentUri);

	// Static content
	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
