package com.accenture.trainingcf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.trainingcf.dto.UsersDTO;
import com.accenture.trainingcf.service.UsersService;
import com.sap.cloud.security.xsuaa.token.Token;


@RestController
@RequestMapping("/Users")
public class UsersController {
	
	@Autowired
	UsersService service;
	
	//
	@GetMapping("")
	public List<UsersDTO> findAll(@RequestParam(name="keyword", required=false) String keyword){
		return service.findAll(keyword);
	}
	
	@GetMapping("{userId}")
	public UsersDTO findById(@PathVariable("userId") String id){
		return service.findById(id);
	}
	
	@PostMapping(value = "")
	public UsersDTO createUser(@RequestBody UsersDTO user){
		return service.saveUser(user);
	}
	
	@PutMapping("{userId}")
	public UsersDTO updateUser(@PathVariable("userId") String id, @RequestBody UsersDTO user){
		if(!id.equals(user.getId())) {
			return new UsersDTO();
		}
		return service.saveUser(user);
	}
	
	@DeleteMapping("{userId}")
	public String deleteUser(@PathVariable("userId") String id){
		return Boolean.toString(service.deleteUser(id));
	}
}
