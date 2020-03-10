package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.servlet.http.HttpSession;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unicam.ids.justmeet.IDSJustMeet.model.Subscribe;
import com.unicam.ids.justmeet.IDSJustMeet.service.SubscribeService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserGroupService;

@Controller
public class SubscribeController {

	@Autowired
	SubscribeService subscribeService;
	
	@Autowired
	UserGroupService userGroupService;
	
	/*
	 * INSERIMENTO NUOVA INSCRIZIONE IN UN GRUPPO 
	 */
	
	@RequestMapping(value = { "/insert-subscribe" }, method = RequestMethod.GET)
	public ModelAndView subscribeForm(int id , Model model , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.setAttribute("id", id);
		model.addAttribute("subscribe", new Subscribe());
		modelAndView.setViewName("user-confirm-subscribe");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/insert-subscribe" }, method = RequestMethod.POST)
	public ModelAndView addSubscribe(@Valid Subscribe subscribe, BindingResult bindingResult , HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Errore Iscrizione!");
		}
		int id = (int) session.getAttribute("id");
		subscribeService.addSubscribe(subscribe, userGroupService.findById(id));
		modelAndView.setViewName("redirect:/see-groups");
		return modelAndView;
	}
	
	/*
	 * RIMOZIONE ISCRIZIONE A GRUPPO DA PARTE DI UN USER
	 */
	
	@RequestMapping(value= "/deleteMySubscribe/{id}" , method= RequestMethod.POST)
    public String deleteMySubscribe(@PathVariable int id){
        Subscribe subscribe = subscribeService.findById(id);
        int idsubscribe = subscribe.getId();
        subscribeService.delteMySubscribe(idsubscribe);
        return "redirect:/my-groups-user";
    }
	
	/*
	 * VISUALIZZAZIONE DEGLI ISCRITTI IN UN GRUPPO
	 */
	
	@RequestMapping(value = { "/see-subscribes/{id}" }, method = RequestMethod.GET)
	public ModelAndView seeSubscribes(@PathVariable("id") int id , Model model ) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("subscribes", subscribeService.findByIdGroup(id));
		modelAndView.setViewName("user-subscribes-list");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/see-subscribes-admin/{id}" }, method = RequestMethod.GET)
	public ModelAndView seeSubscribesAdmin(@PathVariable("id") int id , Model model ) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("subscribes", subscribeService.findByIdGroup(id));
		modelAndView.setViewName("admin-subscribes-list");
		return modelAndView;
	}
	
	
	
	
	/*
	 * RIMOZIONE ISCRITTO A GRUPPO DA PARTE DEL CREATORE DEL GRUPPO
	 */
	@RequestMapping(value= "/deleteUserSubscribe/{id}" , method= RequestMethod.POST)
    public String deleteUserSubscribe(@PathVariable int id){
        Subscribe subscribe = subscribeService.findById(id);
        int idsubscribe = subscribe.getId();
        subscribeService.delteMySubscribe(idsubscribe);
        return "redirect:/my-groups-user";
    }
	
	@RequestMapping(value= "/deleteUserSubscribeAdmin/{id}" , method= RequestMethod.POST)
    public String deleteUserSubscribeAdmin(@PathVariable int id){
        Subscribe subscribe = subscribeService.findById(id);
        int idsubscribe = subscribe.getId();
        subscribeService.delteMySubscribe(idsubscribe);
        return "redirect:/my-groups-admin";
    }
	
	
}
