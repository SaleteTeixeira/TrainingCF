package com.accenture.trainingcf.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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

import com.accenture.trainingcf.dto.UsersDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersControllerTest {
	
	@Autowired
	private UsersController controller;
	
	// Mock testing variables
	private static MockMvc mockMvc;
	private static ObjectMapper mapper;
	private static UsersDTO user;

	private static void getUserTest() {
		UsersDTO UsersDTO = new UsersDTO();
		UsersDTO.setName("User Test");
		user = UsersDTO;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		getUserTest();
	}

	@Before
	public void setUpBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void aa_saveUser() throws UnsupportedEncodingException, Exception {

		final byte[] userAsByteArray = mapper.writeValueAsBytes(user);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/Users")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(userAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final UsersDTO objResult = mapper.readValue(result, UsersDTO.class);

		assertThat(objResult.getId()).isNotEmpty();
		user.setId(objResult.getId());
	}
	
	@Test
	public void ab_changeUser() throws UnsupportedEncodingException, Exception {

		String newName = "User Test Altered";
		user.setName(newName);
		final byte[] userAsByteArray = mapper.writeValueAsBytes(user);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/Users/" + user.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(userAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final UsersDTO objResult = mapper.readValue(result, UsersDTO.class);

		assertThat(objResult.getId()).isEqualTo(user.getId());
		assertThat(objResult.getName()).isEqualTo(newName);

	}
	
	@Test
	public void ac_getAllUsers() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Users")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<UsersDTO> objResult = Arrays.asList(mapper.readValue(result, UsersDTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);

	}
	
	@Test
	public void acb_getAllUsersWithFuzzy() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Users?keyword=User Test")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<UsersDTO> objResult = Arrays.asList(mapper.readValue(result, UsersDTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);

	}
	
	@Test
	public void ad_getOneUser() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Users/" + user.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final UsersDTO objResult = mapper.readValue(result, UsersDTO.class);
		assertThat(objResult.getId()).isEqualTo(user.getId());

	}

	@Test
	public void az_deleteUser() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/Users/" + user.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();

	}
}
