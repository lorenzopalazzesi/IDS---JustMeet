package com.unicam.ids.justmeet.IDSJustMeet.controller;


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
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;
import com.unicam.ids.justmeet.IDSJustMeet.service.SubscribeService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserGroupService;
import com.unicam.ids.justmeet.IDSJustMeet.service.UserService;


 
@Controller
public class UserGroupController {

	@Autowired
	UserGroupService userGroupService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	SubscribeService subscribeService;
	
	/*
	 * VISUALIZZAZIONE GRUPPI PRESENTI NELLA PIATTAFORMA
	 */
	
	@RequestMapping(value = "/see-groups", method = RequestMethod.GET)
	public ModelAndView listGroups(Model model, @RequestParam (defaultValue="") String name) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("groups",userGroupService.findByName(name));
		modelAndView.setViewName("user-group-list"); // resources/template/user-list.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/see-groups-admin", method = RequestMethod.GET)
	public ModelAndView listGroupsAdmin(Model model, @RequestParam (defaultValue="") String name) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("groups",userGroupService.findByName(name));
		modelAndView.setViewName("admin-group-list"); // resources/template/user-list.html
		return modelAndView;
	}
	
	/*
	 * INSERIMENTO NUOVO GRUPPO 
	 */
	
	
	@RequestMapping(value = { "/insert-group-admin" }, method = RequestMethod.GET)
	public ModelAndView createGroupAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		UserGroup userGroup = new UserGroup();
		modelAndView.addObject("usergroup", userGroup);
		modelAndView.setViewName("admin-insert-group"); // resources/template/insert-group-admin.html
		return modelAndView;
	}
	
	
	@RequestMapping(value = { "/insert-group-user" }, method = RequestMethod.GET)
	public ModelAndView createGroupUser() {
		ModelAndView modelAndView = new ModelAndView();
		UserGroup userGroup = new UserGroup();
		modelAndView.addObject("usergroup", userGroup);
		modelAndView.setViewName("user-insert-group"); // resources/template/insert-group-admin.html
		return modelAndView;
	}
	
	
	@RequestMapping(value= "/insert-group-admin", method=RequestMethod.POST)
	public ModelAndView createNewGroup(@Valid UserGroup userGroup, BindingResult bindingResult , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()){
			modelAndView.addObject("successMessage","Controlla le informazioni inserite per la creazione del Gruppo!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userGroupService.isGroupNameAlreadyPresent(userGroup)){
			modelAndView.addObject("successMessage","NOME DEL GRUPPO GIA' PRESENTE!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else {
			userGroupService.saveGroup(userGroup);
			modelAndView.addObject("successMessage","GRUPPO CREATO CON SUCCESSO!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		modelAndView.addObject("usergroup",new UserGroup());
		modelAndView.setViewName("admin-insert-group");
		return modelAndView;
	}
	
	@RequestMapping(value= "/insert-group-user", method=RequestMethod.POST)
	public ModelAndView createNewGroupUser(@Valid UserGroup userGroup, BindingResult bindingResult , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()){
			modelAndView.addObject("successMessage","Controlla le informazioni inserite per la creazione del Gruppo!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userGroupService.isGroupNameAlreadyPresent(userGroup)){
			modelAndView.addObject("successMessage","NOME DEL GRUPPO GIA' PRESENTE!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else {
			userGroupService.saveGroup(userGroup);
			modelAndView.addObject("successMessage","GRUPPO CREATO CON SUCCESSO!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		modelAndView.addObject("usergroup",new UserGroup());
		modelAndView.setViewName("user-insert-group");
		return modelAndView;
	}
	
	
	/*
	 * VISUALIZZAZIONE DEI PROPRI GRUPPI
	 */
	
	@RequestMapping(value = { "/my-groups-admin" }, method = RequestMethod.GET)
	public String myGroupsAdmin(@ModelAttribute("usergroup") @Valid UserGroup userGroup, BindingResult result, Model model) {
		    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		    String email = loggedInUser.getName(); 
		    User user = userService.findByEmail(email);
		    int id = user.getId();
		    model.addAttribute("usergroups", userGroupService.findByCreator(id));
		    return "admin-my-groups"; //resources/template/my-groups-admin.html
	}
	
	@RequestMapping(value = { "/my-groups-user" }, method = RequestMethod.GET)
	public String myGroupsUser(@ModelAttribute("usergroup") @Valid UserGroup userGroup, BindingResult result, Model model) {
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String email = loggedInUser.getName(); 
			User user = userService.findByEmail(email);
			int id = user.getId();
			model.addAttribute("usergroups", userGroupService.findByCreator(id));
			model.addAttribute("subscribes", subscribeService.findByIdSignedUp(id));
			return "user-my-groups";
	}

	
	/*
	 * OPERAZIONI DI CANCELLAZIONE DEI GRUPPI
	 */
		
	/* Metodo che permette ad un utente Amministratore del Sistema di eliminare un gruppo.*/
	@RequestMapping(value= "/deleteGroup/{id}" , method= RequestMethod.POST)
	public String deleteGroup(@PathVariable int id){
		userGroupService.deleteGroup(id);
	    	return "redirect:/see-groups-admin";
		}
		
	/* Metodo che permette ad un utente Amministratore del Sistema di eliminare un proprio gruppo.*/
	@RequestMapping(value= "/deleteMyGroupAdmin/{id}" , method= RequestMethod.POST)
	public String deleteMyGroupAdmin(@PathVariable int id){
		userGroupService.deleteGroup(id);
	    	return "redirect:/my-groups-admin";
		}
	
	/* Metodo che permette ad un utente User del Sistema di eliminare un gruppo.*/
	@RequestMapping(value= "/deleteMyGroupUser/{id}" , method= RequestMethod.POST)
	public String deleteMyGroupUser(@PathVariable int id){
		userGroupService.deleteGroup(id);
	        return "redirect:/my-groups-user";
	    }
		
		
		
}
