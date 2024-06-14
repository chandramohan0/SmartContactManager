package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Home-Smart contact Manager");
		return "home";
		
	}
	
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","About-Smart contact Manager");
		return "about";
		
	}
	
	@RequestMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("title","Register-Smart contact Manager");
		model.addAttribute("user",new User());
		return "signup";
		
	}
	
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user,BindingResult result1, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model model, HttpSession session) {
		

		try {
			
			if(!agreement) {
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			if(result1.hasErrors()){
				System.out.println("Error : "+user.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement : "+agreement);
			System.out.println("User : "+user);
			
			User result = this.userRepository.save(user);
			model.addAttribute("user",new User());
			
			Message message =  new Message("Successfully Registerd", "alert-success");
			session.setAttribute("message",message);
	        model.addAttribute("session", session);
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			
			Message message =new Message("Something went wrong "+e.getMessage(), "alert-danger");
			session.setAttribute("message",message );
			
			return "signup";
		}
		
		
	}
	
	//handler for custom login
	@GetMapping("/signin")
	public String customLogin(Model model) {
		model.addAttribute("title","Login Page");
		return "login";
		
	}
	
}
