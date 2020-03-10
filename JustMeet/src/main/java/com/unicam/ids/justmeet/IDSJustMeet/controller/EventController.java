package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicam.ids.justmeet.IDSJustMeet.model.Event;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.service.EventService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserGroupService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class EventController {

		@Autowired
		EventService eventService;
		
		@Autowired
		UserService userService;
		
		@Autowired
		UserGroupService userGroupService;
		
		
		
		// INSERIMENTO NUOVO EVENTO // 
		// Creazione di un nuovo evento USER
		@RequestMapping(value = { "/insert-event" }, method = RequestMethod.GET)
		public ModelAndView insert() {
			ModelAndView modelAndView = new ModelAndView();
			Event event = new Event();
			modelAndView.addObject("event", event);
			modelAndView.setViewName("user-insert-event"); // resources/template/login.html
			return modelAndView;
		}
		
		@RequestMapping(value= "/insert-event", method=RequestMethod.POST)
		public ModelAndView insertEvent(@Valid Event event, BindingResult bindingResult , ModelMap modelMap) {
			ModelAndView modelAndView = new ModelAndView();
			// Controllo per le informazioni fornite nel form
			if(bindingResult.hasErrors()){
				modelAndView.addObject("successMessage","Controlla le informazioni inserite per l'Evento!");
				modelMap.addAttribute("bindingResult", bindingResult);
			}
			// Controllo se il titolo dell'evento è gia presente
			else if(eventService.isEventTitleAlreadyPresent(event)){
				modelAndView.addObject("successMessage","Titolo dell'evento già presente!");
				modelMap.addAttribute("bindingResult", bindingResult);
			}
			//Salvataggio event, no errori
			else {
				eventService.saveEvent(event);
				modelAndView.addObject("successMessage","Evento Creato con Successo!");
				modelMap.addAttribute("bindingResult", bindingResult);
			}
			
			modelAndView.addObject("event",new Event());
			modelAndView.setViewName("user-insert-event");
			return modelAndView;
		}
		
		// Inserimento nuovo evento ADMIN //
		@RequestMapping(value = { "/insert-event-admin" }, method = RequestMethod.GET)
		public ModelAndView insertEventByAdmin() {
			ModelAndView modelAndView = new ModelAndView();
			Event event = new Event();
			modelAndView.addObject("event", event);
			modelAndView.setViewName("admin-insert-event"); // resources/template/login.html
			return modelAndView;
		}
		
		/* Metodo che permette ad un Amministratore del sistema di inserire un nuovo evento */
		@RequestMapping(value= "/insert-event-admin", method=RequestMethod.POST)
		public ModelAndView insertEventByAdmin(@Valid Event event, BindingResult bindingResult , ModelMap modelMap) {
			ModelAndView modelAndView = new ModelAndView();
			// Controllo per le informazioni fornite nel form
			if(bindingResult.hasErrors()){
				modelAndView.addObject("successMessage","Controlla le informazioni inserite per l'Evento!");
				modelMap.addAttribute("bindingResult", bindingResult);
			}
			// Controllo se il titolo dell'evento è gia presente
			else if(eventService.isEventTitleAlreadyPresent(event)){
				modelAndView.addObject("successMessage","Titolo dell'evento già presente!");
				modelMap.addAttribute("bindingResult", bindingResult);
			}
			//Salvataggio Evento, non ci sono errori
			else {
				eventService.saveEvent(event);
				modelAndView.addObject("successMessage","Evento Creato con Successo!");
				modelMap.addAttribute("bindingResult", bindingResult);
			}
			
			modelAndView.addObject("event",new Event());
			modelAndView.setViewName("admin-insert-event");
			return modelAndView;
		}
		
		// VISUALIZZAZIONE DEGLI EVENTI PRESENTI NELLA PIATTAFORMA //
		@RequestMapping(value = "/see-events", method = RequestMethod.GET)
		public ModelAndView listEvent(Model model, @RequestParam (defaultValue="") String title , HttpSession session) {
			session.setAttribute("title", title);
			ModelAndView modelAndView = new ModelAndView();
			model.addAttribute("events",eventService.findByTitle(title));
			modelAndView.setViewName("user-event-list"); // resources/template/user-list.html
			return modelAndView;
		}
		
		/* Metodo che permette ad un Amministratore del sistema di vedere gli Eventi presenti nel Sistema*/
		@RequestMapping(value = "/see-events-admin", method = RequestMethod.GET)
		public ModelAndView adminListEvent(Model model, @RequestParam (defaultValue="") String title) {
			ModelAndView modelAndView = new ModelAndView();
			model.addAttribute("events",eventService.findByTitle(title));
			modelAndView.setViewName("admin-event-list"); // resources/template/user-list.html
			return modelAndView;
		}
		
		
		
		// VISUALIZZAZIONE DEI PROPRI EVENTI CREATI // 
		
		@RequestMapping(value = { "/my-events-user" }, method = RequestMethod.GET)
		public String myEvents(@ModelAttribute("event") @Valid Event event, BindingResult result, Model model) {
			    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int id = user.getId();
			    model.addAttribute("events", eventService.findByCreator(id));
			    return "user-my-events"; //resources/template/my-groups-admin.html
			  }
		
		@RequestMapping(value = { "/my-events-admin" }, method = RequestMethod.GET)
		public String myEventsAdmin(@ModelAttribute("event") @Valid Event event, BindingResult result, Model model) {
			    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int id = user.getId();
			    model.addAttribute("events", eventService.findByCreator(id));
			    return "admin-my-events"; //resources/template/my-groups-admin.html
			  }
		
		// ELIMINAZIONE DEI PROPRI EVENTI CREATI // 
		
		/* Metodo che permette ad un utente di eliminare un suo evento.*/
		@RequestMapping(value= "/deleteMyEvent/{id}" , method= RequestMethod.POST)
	    public String deleteMyEvent(@PathVariable int id){
	        eventService.deleteEvent(id);
	        return "redirect:/my-events-user";
	    }
		
		/* Metodo che permette ad un utente di eliminare un suo evento.*/
		@RequestMapping(value= "/deleteMyEventAdmin/{id}" , method= RequestMethod.POST)
	    public String deleteMyEventAdmin(@PathVariable int id){
	        eventService.deleteEvent(id);
	        return "redirect:/my-events-admin";
	    }
		
		//APERTURA E CHIUSURA DEGLI EVENTI // 
		
		/* Metodo che permette ad un utente di chiudere le iscrizioni al suo evento.*/
		@RequestMapping(value= "/close-event-admin/{id}" , method= RequestMethod.POST)
	    public String closeEventAdmin(@PathVariable int id){
	        eventService.closeMyEvent(id);
	        return "redirect:/my-events-admin";
	    }
		
		/* Metodo che permette ad un utente di aprire le iscrizioni al suo evento.*/
		@RequestMapping(value= "/open-event-admin/{id}" , method= RequestMethod.POST)
	    public String openEventAdmin(@PathVariable int id){
	        eventService.openMyEvent(id);
	        return "redirect:/my-events-admin";
	    }
		
		/* Metodo che permette ad un utente di chiudere le iscrizioni al suo evento.*/
		@RequestMapping(value= "/close-event/{id}" , method= RequestMethod.POST)
	    public String closeEvent(@PathVariable int id){
	        eventService.closeMyEvent(id);
	        return "redirect:/my-events-user";
	    }
		
		/* Metodo che permette ad un utente di aprire le iscrizioni al suo evento.*/
		@RequestMapping(value= "/open-event/{id}" , method= RequestMethod.POST)
	    public String openEvent(@PathVariable int id){
	        eventService.openMyEvent(id);
	        return "redirect:/my-events-user";
	    }
		
		
}
