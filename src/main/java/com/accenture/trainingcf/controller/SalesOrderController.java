package com.accenture.trainingcf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.trainingcf.dto.SalesOrderDTO;
import com.accenture.trainingcf.service.SalesOrderService;

@RestController
@RequestMapping("/SalesOrder")
public class SalesOrderController {
	
	@Autowired
	SalesOrderService service;

	@GetMapping("")
	public List<SalesOrderDTO> findAll(@RequestParam(name="keyword", required=false) String keyword){
		return service.findAll(keyword);
	}
	
	@GetMapping("{salesOrderId}")
	public SalesOrderDTO findById(@PathVariable("salesOrderId") String id){
		return service.findById(id);
	}
	
	@PostMapping(value = "")
	public SalesOrderDTO createSalesOrder(@RequestBody SalesOrderDTO salesOrder){
		return service.saveSalesOrder(salesOrder);
	}
	
	@PutMapping("{salesOrderId}")
	public SalesOrderDTO updateSalesOrder(@PathVariable("salesOrderId") String id, @RequestBody SalesOrderDTO salesOrder){
		if(!id.equals(salesOrder.getId())) {
			return new SalesOrderDTO();
		}
		return service.saveSalesOrder(salesOrder);
	}
	
	@DeleteMapping("{salesOrderId}")
	public String deleteSalesOrder(@PathVariable("salesOrderId") String id){
		return Boolean.toString(service.deleteSalesOrder(id));
	}
}
