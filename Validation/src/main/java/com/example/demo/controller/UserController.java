package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto, BindingResult result) {

		if (result.hasErrors()) {

			Map<String, String> errors = new HashMap<String, String>();

			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

		}
		
		User user = new User();
		user.setName(dto.getName());
		user.setAddress(dto.getAddress());
		user.setAge(dto.getAge());
		user.setEmail(dto.getEmail());
		
		service.saveUser(user);
		
		return new ResponseEntity<>("user is valid and saved successfully",HttpStatus.OK);

	}
	
	@GetMapping
	public List<User> getAll(){
		
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getById(@PathVariable Integer id) {
		
		return service.getById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		
		service.deleteByID(id);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable Integer id,@RequestBody User user) {
		
		return service.updateUser(id, user);
	}
}
