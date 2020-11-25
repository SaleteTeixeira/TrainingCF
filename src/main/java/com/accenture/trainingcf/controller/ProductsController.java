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

import com.accenture.trainingcf.dto.ProductsDTO;
import com.accenture.trainingcf.service.ProductsService;
import com.sap.cloud.security.xsuaa.token.Token;

@RestController
@RequestMapping("/Products")
public class ProductsController {
	
	@Autowired
	ProductsService service;
	
	@GetMapping("")
	public List<ProductsDTO> findAll(@RequestParam(value="keyword", required=false) String keyword, @AuthenticationPrincipal Token token){
		return service.findAll(keyword);
	}
	
	@GetMapping("{productId}")
	public ProductsDTO findById(@PathVariable("productId") String id, @AuthenticationPrincipal Token token){
		return service.findById(id);
	}
	
	@PostMapping(value = "")
	public ProductsDTO createProduct(@RequestBody ProductsDTO product, @AuthenticationPrincipal Token token){
		return service.saveProduct(product);
	}
	
	@PutMapping("{productId}")
	public ProductsDTO updateProduct(@PathVariable("productId") String id, @RequestBody ProductsDTO product, @AuthenticationPrincipal Token token){
		if(!id.equals(product.getId())) {
			return new ProductsDTO();
		}
		return service.saveProduct(product);
	}
	
	@DeleteMapping("{productId}")
	public String deleteProduct(@PathVariable("productId") String id, @AuthenticationPrincipal Token token){
		return Boolean.toString(service.deleteProduct(id));
	}
}
