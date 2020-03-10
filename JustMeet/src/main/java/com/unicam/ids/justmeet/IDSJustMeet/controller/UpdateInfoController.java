package com.unicam.ids.justmeet.IDSJustMeet.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicam.ids.justmeet.IDSJustMeet.model.Event;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;
import com.unicam.ids.justmeet.IDSJustMeet.service.EventService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserGroupService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;

@Controller
public class UpdateInfoController {

	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserGroupService userGroupService;
	
	
			/*
		 	*  MODIFICA PER I PROPRI EVENTI
		 	*/
	
			@RequestMapping(value = { "/edit/myEvent/user/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateMyEventUser(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				Event event = eventService.findById(id);
		        model.addAttribute("event", event);
		        modelAndView.setViewName("user-update-my-event");
		        return modelAndView;
		    }
			
			@RequestMapping(value = {"/update/myEvent/user"})
		    public ModelAndView updateMyEventUser(@RequestParam("id") int id, @Valid Event event, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int idCurrentUser = user.getId();
				
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		        	event.setId(id);
		        	modelAndView.addObject("successMessage","Controlla le informazioni !");
		    	}
		    	eventService.saveEvent(event);
		        modelMap.addAttribute("event", eventService.findByCreator(idCurrentUser));
		        modelAndView.setViewName("redirect:/my-events-user");
		        return modelAndView;
		    
		    	}
			
			/*Metodo che permette ad un utente amministratore di modificare i dati relativi ad un suo evento*/
			@RequestMapping(value = { "/edit/myEvent/admin/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateMyEventAdmin(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				Event event = eventService.findById(id);
		        model.addAttribute("event", event);
		        modelAndView.setViewName("admin-update-my-event");
		        return modelAndView;
		    }
			
			@RequestMapping(value = {"/update/myEvent/admin"})
		    public ModelAndView updateMyEventAdmin(@RequestParam("id") int id, @Valid Event event, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int idCurrentUser = user.getId();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		        	event.setId(id);
		        	modelAndView.addObject("successMessage","Controlla le informazioni !");
		    	}
		    	eventService.saveEvent(event);
		        modelMap.addAttribute("event", eventService.findByCreator(idCurrentUser));
		        modelAndView.setViewName("redirect:/my-events-admin");
		        return modelAndView ;
		    
		    	}
			
			/*
			 * MODIFICA EVENTI DA PARTE DI UN AMMINISTRATORE
			 */
			
			/*Metodo che permette ad un utente amministratore di modificare i dati relativi ad un suo evento*/
			@RequestMapping(value = { "/edit/Event/admin/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateEventAdmin(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				Event event = eventService.findById(id);
		        model.addAttribute("event", event);
		        modelAndView.setViewName("admin-update-event");
		        return modelAndView;
		    }
			
			@RequestMapping(value = {"/update/Event/admin"})
		    public ModelAndView updateEventAdmin(@RequestParam("id") int id, @Valid Event event, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		        	event.setId(id);
		        	modelAndView.addObject("successMessage","Controlla le informazioni !");
		    	}
		    	eventService.saveEvent(event);
		        modelMap.addAttribute("event", eventService.findAll());
		        modelAndView.setViewName("redirect:/see-events-admin");
		        return modelAndView;
		    
		    	}
			
			/*
			 * MODIFICA PROPRI DATI UTENTE
			 */
			
			@RequestMapping(value = { "/edit-info-user/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateUser(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				User user = userService.findById(id);
		        model.addAttribute("user", user);
		        modelAndView.setViewName("user-update-my-info");
		        return modelAndView;
		    }
			
			
			@RequestMapping(value = {"/update-info-user"}, method = RequestMethod.POST)
		    public ModelAndView updateUser(@RequestParam("id") int id, @Valid User user, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		        	user.setId(id);
		        	modelAndView.addObject("successMessage","Controlla le informazioni !");
		        }
		    	userService.saveUser(user);
		        modelMap.addAttribute("user", userService.findById(id));
		        modelAndView.setViewName("redirect:/profile-page");
		        return modelAndView ;
		    
		    	}
			
			@RequestMapping(value = { "/edit-info-admin/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateAdmin(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				User user = userService.findById(id);
		        model.addAttribute("user", user);
		        modelAndView.setViewName("admin-update-my-info");
		        return modelAndView;
		    }
			
			
			@RequestMapping(value = {"/update-info-admin"}, method = RequestMethod.POST)
		    public ModelAndView updateAdmin(@RequestParam("id") int id, @Valid User user, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		        	user.setId(id);
		        	modelAndView.addObject("successMessage","Controlla le informazioni !");
		        }
		    	userService.saveUser(user);
		        modelMap.addAttribute("user", userService.findById(id));
		        modelAndView.setViewName("redirect:/profile-page-admin");
		        return modelAndView;
		    
		    	}
			/*
			 * MODIFICA DATI UTENTI ADMIN
			 */
			
			@RequestMapping(value = { "/edit-info-user-admin/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateInfoUserAdmin(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				User user = userService.findById(id);
		        model.addAttribute("user", user);
		        modelAndView.setViewName("admin-update-user-info");
		        return modelAndView;
		    }
			
			
			@RequestMapping(value = {"/update-info-user-admin"}, method = RequestMethod.POST)
		    public ModelAndView updateInfoUserAdmin(@RequestParam("id") int id, @Valid User user, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		        	user.setId(id);
		        	modelAndView.addObject("successMessage","Controlla le informazioni !");
		        }
		    	userService.saveUser(user);
		        modelMap.addAttribute("user", userService.findById(id));
		        modelAndView.setViewName("redirect:/profile-page-admin");
		        return modelAndView;
		    
		    	}
			
			/*
			 * MODIFICA DATI GRUPPO
			 */
			
			@RequestMapping(value = { "/edit/myGroup/user/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateMyGroupUser(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				UserGroup userGroup = userGroupService.findById(id);
		        model.addAttribute("usergroup", userGroup);
		        modelAndView.setViewName("user-update-my-group");
		        return modelAndView;
		    }
			
			@RequestMapping(value = {"/update/myGroup/user"})
		    public ModelAndView updateMyGroupUser(@RequestParam("id") int id, @Valid UserGroup userGroup, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int idCurrentUser = user.getId();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		           modelAndView.addObject("successMessage","Controlla le informazioni !");
		    	}
		    	userGroupService.saveGroup(userGroup);
		        modelMap.addAttribute("usergroup", userGroupService.findByCreator(idCurrentUser));
		        modelAndView.setViewName("redirect:/my-groups-user");
		        return modelAndView ;
		    
		    	}
			
			
			/*
			 * MODIFICA DATI DEL PROPRIO GRUPPO DA PARTE DI UN ADMIN
			 */
			
			@RequestMapping(value = { "/edit/myGroup/admin/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView  updateMyGroupAdmin(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				UserGroup userGroup = userGroupService.findById(id);
		        model.addAttribute("usergroup", userGroup);
		        modelAndView.setViewName("admin-update-my-group");
		        return modelAndView;
		    }
			
			@RequestMapping(value = {"/update/myGroup/admin"})
		    public ModelAndView updateMyGroupAdmin(@RequestParam("id") int id, @Valid UserGroup userGroup, BindingResult result, ModelMap modelMap) {
				ModelAndView modelAndView = new ModelAndView();
				Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int idCurrentUser = user.getId();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		           modelAndView.addObject("successMessage","Controlla le informazioni !");
		    	}
		    	userGroupService.saveGroup(userGroup);
		        modelMap.addAttribute("usergroup", userGroupService.findByCreator(idCurrentUser));
		        modelAndView.setViewName("redirect:/my-groups-admin");
		        return modelAndView;
		    
		    	}
			
			/*
			 * MODIFICA DATI GRUPPI DA PARTE DI UN ADMIN
			 */
			
			@RequestMapping(value = { "/edit/group/admin/{id}"  }, method = RequestMethod.GET)
		    public ModelAndView updateGroupAdmin(@PathVariable("id") int id, Model model) {
				ModelAndView modelAndView = new ModelAndView();
				UserGroup userGroup = userGroupService.findById(id);
		        model.addAttribute("usergroup", userGroup);
		        modelAndView.setViewName("admin-update-group");
		        return modelAndView;
		    }
			
			@RequestMapping(value = {"/update/group/admin"})
		    public String updateGroupAdmin(@RequestParam("id") int id, @Valid UserGroup userGroup, BindingResult result, ModelMap modelMap) {
				Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 
			    User user = userService.findByEmail(email);
			    int idCurrentUser = user.getId();
				if (result.hasErrors()) {
		           System.out.println(result.getAllErrors());
		            return "redirect:/see-groups-admin";
		    	}
		    	userGroupService.saveGroup(userGroup);
		        modelMap.addAttribute("usergroup", userGroupService.findByCreator(idCurrentUser));
		        return "redirect:/see-groups-admin";
		    
		    	}
}
