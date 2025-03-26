package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public User saveUser (User user) {
		
		return repository.save(user);
	}
	
	public List<User> getAllUsers(){
		
		return repository.findAll(); 
	}
	
	public User getById(Integer id) {
		
		return repository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found : "+id));
	}
	
	public void deleteByID(Integer id) {
		
		repository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found : "+id));
		repository.deleteById(id);
	}
	
	public User updateUser(Integer id,User user) {
		User existingUser = repository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found : "+id));
		
		existingUser.setName(user.getName());
		existingUser.setAddress(user.getAddress());
		existingUser.setEmail(user.getEmail());
		existingUser.setAge(user.getAge());
		
		User savedUser = repository.save(user);
		return savedUser;
	}
	
}
