package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.service.FeedBackService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	FeedBackService feedBackService;
	
	/*
	 * VISUALIZZAZIONE PER UN USER DELLA LISTA DEGLI UTENTI PRESENTI NELLA PIATTAFORMA
	 */
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUser(Model model, @RequestParam (defaultValue="") String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user-list"); // resources/template/user-list.html
		model.addAttribute("users",userService.findByName(name));
		return modelAndView;
	}
	
	
	
	/*
	 * PAGINA PROFILO USER
	 */
	
	@RequestMapping(value = { "/profile-page" }, method = RequestMethod.GET)
	public ModelAndView currentUser(@ModelAttribute("user") @Valid User userDto, BindingResult result, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    User user = userService.findByEmail(email);
	    model.addAttribute("name", user.getName());
	    model.addAttribute("emailAddress", email);
	    model.addAttribute("lastname",user.getLastName());
	    model.addAttribute("id", user.getId());
	    model.addAttribute("status", user.getStatus());
	    model.addAttribute("feedbacks", feedBackService.findByUser(user.getId()));
	    modelAndView.setViewName("user-profile-page");
	    return modelAndView; //resources/template/profile-page.html
	  }
	
	
}
