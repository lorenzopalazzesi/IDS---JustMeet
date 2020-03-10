package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.servlet.http.HttpSession;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.unicam.ids.justmeet.IDSJustMeet.model.Event;
import com.unicam.ids.justmeet.IDSJustMeet.model.Prenotation;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.service.EventService;
import com.unicam.ids.justmeet.IDSJustMeet.service.PrenotationService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class PrenotationController {

	@Autowired
	PrenotationService prenotationService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	/*
	 * OPERAZIONI SULLE PRENOTAZIONI DA PARTE DI UN USER
	 */
	@RequestMapping(value = { "/insert-prenotation" }, method = RequestMethod.GET)
	public ModelAndView prenotationForm(int id , Model model , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.setAttribute("id", id);
		model.addAttribute("prenotation", new Prenotation());
		modelAndView.setViewName("user-confirm-prenotation");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/insert-prenotation" }, method = RequestMethod.POST)
	public ModelAndView addPrenotation(@Valid Prenotation prenotation, BindingResult bindingResult , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Controlla l'inserimento della Prenotazione!");
		}
		int id = (int) session.getAttribute("id");
		prenotationService.addPrenotation(prenotation, eventService.findById(id));
		modelAndView.setViewName("redirect:/see-events");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/my-prenotations-user" }, method = RequestMethod.GET)
	public ModelAndView myPrenotationUser(@ModelAttribute("prenotation") @Valid Prenotation prenotation, Model model) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		    String email = loggedInUser.getName(); 
		    User user = userService.findByEmail(email);
		    int id = user.getId();
		    model.addAttribute("prenotations", prenotationService.findByUser(id));
		    modelAndView.setViewName("user-my-prenotations");
		    return modelAndView;
		  }
	
	@RequestMapping(value= "/deleteMyPrenotation/{id}" , method= RequestMethod.POST)
    public String deleteMyPrenotation(@PathVariable int id){
        Prenotation prenotation = prenotationService.findById(id);
        int idevent = prenotation.getIdevent();
        Event event = eventService.findById(idevent);
        event.setMaxpartecipants(event.getMaxpartecipants()+1);
		prenotationService.deleteMyPrenotation(id);
        return "redirect:/my-prenotations-user";
    }
	
	
	
	
	/*
	 * OPERAZIONI SULLE PRENOTAZIONI DA PARTE DI UN ADMIN
	 */
	
	@RequestMapping(value = { "/insert-prenotation-admin" }, method = RequestMethod.GET)
	public ModelAndView prenotationFormAdmin(int id , Model model , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.setAttribute("id", id);
		model.addAttribute("prenotation", new Prenotation());
		modelAndView.setViewName("admin-confirm-prenotation");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/insert-prenotation-admin" }, method = RequestMethod.POST)
	public ModelAndView addPrenotationAdmin(@Valid Prenotation prenotation, BindingResult bindingResult , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Controlla l'inserimento della Prenotazione!");
		}
		int id = (int) session.getAttribute("id");
		prenotationService.addPrenotation(prenotation, eventService.findById(id));
		modelAndView.setViewName("redirect:/see-events-admin");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/my-prenotations-admin" }, method = RequestMethod.GET)
	public ModelAndView myPrenotationAdmin(@ModelAttribute("prenotation") @Valid Prenotation prenotation, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		    String email = loggedInUser.getName(); 
		    User user = userService.findByEmail(email);
		    int id = user.getId();
		    model.addAttribute("prenotations", prenotationService.findByUser(id));
		    modelAndView.setViewName("admin-my-prenotation");
		    return modelAndView ;
		  }
	
	@RequestMapping(value= "/deleteMyPrenotationAdmin/{id}" , method= RequestMethod.POST)
    public String deleteMyPrenotationAdmin(@PathVariable int id){
        Prenotation prenotation = prenotationService.findById(id);
        int idevent = prenotation.getIdevent();
        Event event = eventService.findById(idevent);
        event.setMaxpartecipants(event.getMaxpartecipants()+1);
		prenotationService.deleteMyPrenotation(id);
        return "redirect:/my-prenotations-admin";
    }

}
