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

import com.accenture.trainingcf.dto.ClientsDTO;
import com.accenture.trainingcf.service.ClientsService;
import com.sap.cloud.security.xsuaa.token.Token;


@RestController
@RequestMapping("/Clients")
public class ClientsController {
	
	@Autowired
	ClientsService service;
	
	@GetMapping("")
	public List<ClientsDTO> findAll(@RequestParam(name="keyword", required=false) String keyword, @AuthenticationPrincipal Token token){
		return service.findAll(keyword);
	}
	
	@GetMapping("{clientId}")
	public ClientsDTO findById(@PathVariable("clientId") String id, @AuthenticationPrincipal Token token){
		return service.findById(id);
	}
	
	@PostMapping(value = "")
	public ClientsDTO createClient(@RequestBody ClientsDTO client, @AuthenticationPrincipal Token token){
		return service.saveClient(client);
	}
	
	@PutMapping("{clientId}")
	public ClientsDTO updateClient(@PathVariable("clientId") String id, @RequestBody ClientsDTO client, @AuthenticationPrincipal Token token){
		if(!id.equals(client.getId())) {
			return new ClientsDTO();
		}
		return service.saveClient(client);
	}
	
	@DeleteMapping("{clientId}")
	public String deleteClient(@PathVariable("clientId") String id, @AuthenticationPrincipal Token token){
		return Boolean.toString(service.deleteClient(id));
	}
}
