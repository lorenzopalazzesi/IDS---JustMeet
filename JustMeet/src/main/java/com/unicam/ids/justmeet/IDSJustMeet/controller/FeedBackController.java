package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unicam.ids.justmeet.IDSJustMeet.model.FeedBack;
import com.unicam.ids.justmeet.IDSJustMeet.service.FeedBackService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class FeedBackController {

	@Autowired
	FeedBackService feedbackService;
	
	@Autowired
	UserService userService;
	
	/*
	 *  INSERIMENTO FEEDBACK 
	 */
	
	@RequestMapping(value = { "/insert-feedback" }, method = RequestMethod.GET)
	public ModelAndView feedBackForm(int id , Model model , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.setAttribute("id", id);
		model.addAttribute("feedback", new FeedBack());
		modelAndView.setViewName("user-insert-feedback");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/insert-feedback" }, method = RequestMethod.POST)
	public ModelAndView addFeedBack(@Valid FeedBack feedback, BindingResult bindingResult , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Controlla l'inserimento del FeedBack!");
		}
		int id = (int) session.getAttribute("id");
		feedbackService.addFeedBack(feedback, userService.findById(id));
		modelAndView.setViewName("redirect:/list");
		return modelAndView;
	}
	
}
