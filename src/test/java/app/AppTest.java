package app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.controllers.UserController;
import app.dtos.PhoneDto;
import app.dtos.UserDto;
import app.handlers.JWTHandler;
import app.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class AppTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private JWTHandler jwtHandler;

	@MockBean
	private EntityManagerFactory entityManagerFactory;

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void get() throws Exception {
		final UUID id = UUID.randomUUID();
		final UserDto user = UserDto.builder().id(id).email("test@test.com").build();

		Mockito.when(userService.get(Mockito.any())).thenReturn(user);

		final JSONObject body = new JSONObject();
		body.put("id", id);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user").
			contentType(MediaType.APPLICATION_JSON).content(body.toString()).accept(MediaType.APPLICATION_JSON);

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final JSONObject obj = new JSONObject(result.getResponse().getContentAsString());

		assertEquals(obj.getJSONObject("data").get("email"), user.getEmail());
	}

	@Test
	public void getAll() throws Exception {
		final List<UserDto> users = new ArrayList<>();

		users.add(UserDto.builder().email("test@a.com").build());
		users.add(UserDto.builder().email("test@b.com").build());

		Mockito.when(userService.getAll()).thenReturn(users);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/all").
			contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final JSONObject obj = new JSONObject(result.getResponse().getContentAsString());

		assertEquals(obj.getJSONArray("data").getJSONObject(0).get("email"), users.get(0).getEmail());
		assertEquals(obj.getJSONArray("data").getJSONObject(1).get("email"), users.get(1).getEmail());
	}

	@Test
	public void save() throws Exception {
		final UUID id = UUID.randomUUID();
		final UserDto user = UserDto.builder().id(id).email("test@test.com").name("Pedro Perez").password("Abc123").
			phones(Arrays.asList(PhoneDto.builder().cityCode("1").countryCode("2").number("55555").build())).
			build();

		Mockito.when(userService.save(Mockito.any())).thenReturn(user);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user").
			contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user));

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final JSONObject obj = new JSONObject(result.getResponse().getContentAsString());

		assertEquals(obj.getJSONObject("data").get("email"), user.getEmail());
	}

	@Test
	public void update() throws Exception {
		final UUID id = UUID.randomUUID();
		final UserDto user = UserDto.builder().id(id).email("edited@test.com").name("Pedro Perez").password("A0c123").
			phones(Arrays.asList(PhoneDto.builder().cityCode("1").countryCode("2").number("22222").build())).
			build();

		Mockito.when(userService.update(Mockito.any())).thenReturn(Boolean.TRUE);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user").
			contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user));

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final JSONObject obj = new JSONObject(result.getResponse().getContentAsString());

		assertEquals(obj.get("mensaje"), "Updated successfully!");
	}

	@Test
	public void delete() throws Exception {
		Mockito.when(userService.deleteById(Mockito.any())).thenReturn(Boolean.TRUE);

		final JSONObject body = new JSONObject();
		body.put("id", UUID.randomUUID());

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user").
			contentType(MediaType.APPLICATION_JSON).content(body.toString()).accept(MediaType.APPLICATION_JSON);

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final JSONObject obj = new JSONObject(result.getResponse().getContentAsString());

		assertEquals(obj.get("mensaje"), "Deteled successfully!");
	}
}
