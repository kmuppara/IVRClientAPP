package com.example.ivrclientapp;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example"})
public class WebMVCConfig extends WebMvcConfigurerAdapter{
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.js/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/*.css/**").addResourceLocations("/resources/");
    }
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("").setViewName("redirect:/ivrhome");
		registry.addViewController("/ivrhome").setViewName("ivrhome");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/*").setViewName("redirect:/ivrhome");
    }
	
	public FreeMarkerConfigurer freemarkerConfigurer() throws Exception{
		FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
		factory.setTemplateLoaderPaths(new String[] {"classpath:org/springframework/web/servlet/view/freemarker","classpath:/templates/","/templates"});
		factory.setDefaultEncoding("UTF-8");
		factory.setPreferFileSystemAccess(false);
		FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		result.setConfiguration(factory.createConfiguration());
		return result;
	}
	
	@Bean
	public RestTemplate restTemplate() throws SecurityException{
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mj2hmc = new MappingJackson2HttpMessageConverter();
		mj2hmc.setSupportedMediaTypes(Arrays.asList(MediaType.MULTIPART_FORM_DATA,MediaType.APPLICATION_JSON));
		restTemplate.getMessageConverters().add(mj2hmc);
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		return restTemplate;
	}
	
}
