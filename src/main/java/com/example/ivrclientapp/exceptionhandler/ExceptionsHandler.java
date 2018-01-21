package com.example.ivrclientapp.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(value=Exception.class)
	public ModelAndView exception(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/error");
		return mv;
	}
}
