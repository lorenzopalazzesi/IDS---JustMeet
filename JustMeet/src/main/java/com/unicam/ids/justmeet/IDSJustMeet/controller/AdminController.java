package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.service.EventService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class AdminController {

	@Autowired
	UserService userService;
	
	@Autowired
	EventService eventService;
	
	
	//Metodo che permette ad un amministratore di eliminare un'utente dalla piattaforma
    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteUser(id);
        model.addAttribute("user", userService.findAll());
        modelAndView.setViewName("redirect:/list-users-admin");
        return modelAndView;
    }
	
    /* Metodo che permette ad un utente Amministratore del Sistema di eliminare un evento.*/
	@RequestMapping(value= "/deleteEvent/{id}" , method= RequestMethod.POST)
    public ModelAndView deleteEvent(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView();
		eventService.deleteEvent(id);
		modelAndView.setViewName("redirect:/see-events-admin");
        return modelAndView;
    }
	
	
	/* Metodo che permette ad un Amministratore del sistema di vedere gli Utenti iscritti alla piattaforma.*/
	@RequestMapping(value = "/list-users-admin", method = RequestMethod.GET)
	public ModelAndView adminListUser(Model model, @RequestParam (defaultValue="") String name) {
			ModelAndView modelAndView = new ModelAndView();
			model.addAttribute("users",userService.findByName(name));
			modelAndView.setViewName("admin-user-list"); // resources/template/user-list.html
			return modelAndView;
		}
	
	/* Metodo che permette ad un Amministratore del sistema di vedere la sezione riservata alla propria pagina di profilo*/
	@RequestMapping(value = { "/profile-page-admin" }, method = RequestMethod.GET)
	public ModelAndView adminCurrentUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
			ModelAndView modelAndView = new ModelAndView();
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		    String email = loggedInUser.getName(); 
		    User currentUser = userService.findByEmail(email);
		    String firstname = currentUser.getName();
		    String lastname= currentUser.getLastName();
		    int id = currentUser.getId();
		    String status = currentUser.getStatus();
		    model.addAttribute("id", id);
		    model.addAttribute("status", status);
		    model.addAttribute("name", firstname);
		    model.addAttribute("emailAddress", email);
		    model.addAttribute("lastname",lastname);
		    modelAndView.setViewName("admin-profile-page");
		    return modelAndView; //resources/template/admin-profile-page.html
		  }
	
	
    
	
}
