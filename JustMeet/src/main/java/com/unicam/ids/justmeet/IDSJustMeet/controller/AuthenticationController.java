package com.unicam.ids.justmeet.IDSJustMeet.controller;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}
	
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcomepage"); // resources/template/welcomepage.html
		return modelAndView;
	}

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user-home"); // resources/template/home.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-home"); // resources/template/admin.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/welcomepage", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("justmeet-welcomepage"); // resources/template/welcome.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("justmeet-register"); // resources/template/register.html
		return modelAndView;
	}
	
	@RequestMapping(value= "/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Controllo per le informazioni fornite nel form
		if(bindingResult.hasErrors()){
			modelAndView.addObject("successMessage","Controlla le informazioni inserite nel Form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		// Controllo se l'utente è già registrato
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage","Utente già Registrato!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		//Salvataggio utente , no errori
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage","Registrazione avvenuta correttamente!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("justmeet-register");
		return modelAndView;
	}
	
	
}
