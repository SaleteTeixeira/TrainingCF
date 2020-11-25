package com.accenture.trainingcf.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.trainingcf.dto.ClientsDTO;
import com.accenture.trainingcf.dto.ProductsDTO;
import com.accenture.trainingcf.dto.SalesOrderDTO;
import com.accenture.trainingcf.dto.SalesOrderItemDTO;
import com.accenture.trainingcf.dto.UsersDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesOrderControllerTest {

	@Autowired
	SalesOrderController controller;

	// Mock testing variables
	private static MockMvc mockMvc;
	private static ObjectMapper mapper;
	private static SalesOrderDTO salesOrder;

	private static void getSalesOrderTest() {
		ClientsDTO clientsDTO = new ClientsDTO();
		clientsDTO.setId("c1");
		clientsDTO.setName("Maria");
		clientsDTO.setFamilyName("Teixeira");
		clientsDTO.setCreatedAt("2020-11-19T00:00");
		clientsDTO.setCreatedBy("Salete");
		clientsDTO.setAge(22);
		
		UsersDTO UsersDTO = new UsersDTO();
		UsersDTO.setId("u1");
		UsersDTO.setName("Salete");
		UsersDTO.setCreatedAt("2020-11-10T00:00");
		UsersDTO.setCreatedBy("Salete");
		
		ProductsDTO productsTO = new ProductsDTO();
		productsTO.setId("p2");
		productsTO.setName("Ice Cream");
		productsTO.setManufacturer("Candy");
		productsTO.setBasePrice(3.0);
		productsTO.setSalesPrice(4.5);
		productsTO.setQuantity(1);
		productsTO.setValidFrom("2020-11-10T00:00");
		productsTO.setValidTo("2020-11-12T00:00");
		productsTO.setCreatedAt("2020-11-10T00:00");
		productsTO.setCreatedBy("Salete");
		
		SalesOrderDTO SalesOrderDTO = new SalesOrderDTO();
		SalesOrderDTO.setStatus("C");
		
		SalesOrderItemDTO salesOrderItemDTO = new SalesOrderItemDTO();
		salesOrderItemDTO.setStatus("C");
		salesOrderItemDTO.setProduct(productsTO);
		
		SalesOrderItemDTO salesOrderItem2DTO = new SalesOrderItemDTO();
		salesOrderItem2DTO.setStatus("C");
		salesOrderItem2DTO.setProduct(productsTO);
		
		List<SalesOrderItemDTO> items = new ArrayList<SalesOrderItemDTO>();
		items.add(salesOrderItemDTO);
		items.add(salesOrderItem2DTO);
		
		SalesOrderDTO.setItems(items);
		SalesOrderDTO.setClient(clientsDTO);
		SalesOrderDTO.setUser(UsersDTO);
		
		salesOrder = SalesOrderDTO;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		getSalesOrderTest();
	}

	@Before
	public void setUpBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void aa_saveSalesOrder() throws UnsupportedEncodingException, Exception {

		final byte[] userAsByteArray = mapper.writeValueAsBytes(salesOrder);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/SalesOrder")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(userAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderDTO objResult = mapper.readValue(result, SalesOrderDTO.class);

		assertThat(objResult.getId()).isNotEmpty();
		salesOrder.setId(objResult.getId());
		salesOrder.setItems(objResult.getItems());
	}
	
	@Test
	public void ab_changeSalesOrder() throws UnsupportedEncodingException, Exception {

		String newStatus = "O";
		salesOrder.setStatus(newStatus);
		final byte[] userAsByteArray = mapper.writeValueAsBytes(salesOrder);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/SalesOrder/" + salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(userAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderDTO objResult = mapper.readValue(result, SalesOrderDTO.class);

		assertThat(objResult.getId()).isEqualTo(salesOrder.getId());
		assertThat(objResult.getStatus()).isEqualTo(newStatus);

	}
	
	@Test
	public void ac_getAllSalesOrder() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/SalesOrder")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<SalesOrderDTO> objResult = Arrays.asList(mapper.readValue(result, SalesOrderDTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);

	}
	
	@Test
	public void ad_getOneSalesOrder() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/SalesOrder/" + salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderDTO objResult = mapper.readValue(result, SalesOrderDTO.class);
		assertThat(objResult.getId()).isEqualTo(salesOrder.getId());

	}
	
	@Test
	public void az_deleteSalesOrder() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/SalesOrder/" + salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();

	}
}
