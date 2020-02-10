package com.rade.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rade.userprofile.dao.User;
import com.rade.userprofile.repository.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailId(email);
		userRepository.save(user);
		return "User Added";
	}

	@GetMapping(path = "/get/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/get/byfirstname")
	public @ResponseBody User getUserByFirstName(@RequestParam String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	@GetMapping(path = "/get/bylastname")
	public @ResponseBody Iterable<User> getUsersByLastName(@RequestParam String lastName) {
		return userRepository.findByLastName(lastName);
	}

}
