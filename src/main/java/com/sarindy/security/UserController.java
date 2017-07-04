package com.sarindy.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepo;

	@RequestMapping(value = { "/asd", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginPage");
		return modelAndView;
	}
	//
	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	// public ModelAndView logout(HttpServletRequest request,
	// HttpServletResponse response) {
	// Authentication auth =
	// SecurityContextHolder.getContext().getAuthentication();
	// if (auth != null) {
	// new SecurityContextLogoutHandler().logout(request, response, auth);
	// }
	//
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.setViewName("loginPage");
	// return modelAndView;
	//
	// }
	//
	// @RequestMapping(value = "/user/userHome", method = RequestMethod.GET)
	// public ModelAndView userHome() {
	// ModelAndView modelAndView = new ModelAndView();
	// Authentication auth =
	// SecurityContextHolder.getContext().getAuthentication();
	// System.out.println(auth.getName() + "***********" +
	// auth.getAuthorities().toString());
	// User user = userService.findUserByEmail(auth.getName());
	//
	// modelAndView.addObject("userName",
	// "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" +
	// user.getEmail() + ")");
	// modelAndView.addObject("adminMessage", "Content Available Only for Users
	// with User Role");
	// modelAndView.setViewName("user/userHomePage");
	// return modelAndView;
	//
	// }
	//
	// @RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
	// public ModelAndView adminHome() {
	// ModelAndView modelAndView = new ModelAndView();
	// Authentication auth =
	// SecurityContextHolder.getContext().getAuthentication();
	// System.out.println(auth.getName() + "***********" +
	// auth.getAuthorities().toString());
	//
	// User user = userService.findUserByEmail(auth.getName());
	// modelAndView.addObject("userName",
	// "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" +
	// user.getEmail() + ")");
	// modelAndView.addObject("adminMessage", "Content Available Only for Users
	// with Admin Role");
	// modelAndView.setViewName("admin/adminHomePage");
	// return modelAndView;
	//
	// }
	//
	// @RequestMapping(value = "/admin/UserRegistration", method =
	// RequestMethod.GET)
	// public ModelAndView registration() {
	// ModelAndView modelAndView = new ModelAndView();
	// User user = new User();
	// List<Role> roles = new ArrayList<>();
	// roleRepo.findAll().forEach(roles::add);
	//
	// modelAndView.addObject("user", user);
	// modelAndView.addObject("roleList", roles);
	// modelAndView.setViewName("admin/UserRegistrationPage");
	// return modelAndView;
	// }
	//
	// @RequestMapping(value = "/admin/UserRegistration", method =
	// RequestMethod.POST)
	// public ModelAndView createNewUser(@Valid User user, BindingResult
	// bindingResult,
	// @RequestParam(value = "role") String role) {
	// ModelAndView modelAndView = new ModelAndView();
	// User userExists = userService.findUserByEmail(user.getEmail());
	// System.out.println(role);
	// if (userExists != null) {
	// bindingResult.rejectValue("email", "error.user",
	// "There is already a user registered with the email provided");
	// }
	// if (bindingResult.hasErrors()) {
	// modelAndView.setViewName("admin/UserRegistrationPage");
	// } else {
	//
	// userService.saveUser(user, role);
	// List<Role> roles = new ArrayList<>();
	// roleRepo.findAll().forEach(roles::add);
	// modelAndView.addObject("successMessage", "User has been registered
	// successfully");
	//
	// modelAndView.addObject("roleList", roles);
	// modelAndView.addObject("user", new User());
	// modelAndView.setViewName("admin/UserRegistrationPage");
	//
	// }
	// return modelAndView;
	// }

	@RequestMapping("/initRole")
	public String InitRole() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("ADMIN"));
		roles.add(new Role("API"));
		roles.add(new Role("USER"));
		roleRepo.save(roles);

		return "Roles Inited";
	}

	@RequestMapping("/initAdmin")
	public String InitialAdmin() {
		User user = new User("Sarindy", "Ouk", "admin@dnynn.com", "123456", 1);
		userService.saveUser(user, "ADMIN");
		return "Admin Created";
	}

	@RequestMapping("/initApi")
	public String InitialApi() {
		User user = new User("Sarindy", "Ouk", "api@dnynn.com", "123456", 1);
		userService.saveUser(user, "API");

		return "API Created";
	}

	@RequestMapping("/initUser")
	public String InitialUser() {
		User user = new User("Sarindy", "Ouk", "user@dnynn.com", "123456", 1);
		userService.saveUser(user, "USER");
		return "User Created";
	}

}
