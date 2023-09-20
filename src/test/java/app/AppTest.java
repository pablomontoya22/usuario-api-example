package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
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

import app.controllers.TaskController;
import app.entities.Task;
import app.services.TaskService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class AppTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	private final Date current = new Date(1695096098589L);

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void get() throws Exception {
		final Task task = Task.builder().id(1L).descripcion("Task 1").fechaCreacion(current).vigente(true).build();

		Mockito.when(taskService.get(Mockito.anyLong())).thenReturn(task);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/1");

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final long timestamp = mapper.readTree(result.getResponse().getContentAsString()).get("timestamp").asLong();

		final String expected = String.format("{\"code\":200,\"data\":{\"id\":1,\"descripcion\":\"Task 1\","
			+ "\"fechaCreacion\":\"2023-09-19 04:01:38\",\"vigente\":true},\"status\":\"OK\",\"message\":\"\","
			+ "\"timestamp\":%d}", timestamp);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getAll() throws Exception {
		final List<Task> tasks = new ArrayList<>();

		tasks.add(Task.builder().id(1L).descripcion("Task 1").fechaCreacion(current).vigente(true).build());
		tasks.add(Task.builder().id(2L).descripcion("Task 2").fechaCreacion(current).vigente(true).build());

		Mockito.when(taskService.getAll()).thenReturn(tasks);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task");

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final long timestamp = mapper.readTree(result.getResponse().getContentAsString()).get("timestamp").asLong();

		final String expected = String.format("{\"code\":200,\"data\":["
			+ "{\"id\":1,\"descripcion\":\"Task 1\",\"fechaCreacion\":\"2023-09-19 04:01:38\",\"vigente\":true},"
			+ "{\"id\":2,\"descripcion\":\"Task 2\",\"fechaCreacion\":\"2023-09-19 04:01:38\",\"vigente\":true}"
			+ "],\"status\":\"OK\",\"message\":\"\",\"timestamp\":%d}", timestamp);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void save() throws Exception {
		final Task task = Task.builder().id(10L).descripcion("New Task").fechaCreacion(current).vigente(true).build();

		Mockito.when(taskService.save(Mockito.any())).thenReturn(task);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/task").
			contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(task));

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final long timestamp = mapper.readTree(result.getResponse().getContentAsString()).get("timestamp").asLong();

		final String expected = String.format("{\"code\":200,\"data\":{\"id\":10,\"descripcion\":\"New Task\","
			+ "\"fechaCreacion\":\"2023-09-19 04:01:38\",\"vigente\":true},\"status\":\"OK\",\"message\":\"\","
			+ "\"timestamp\":%d}", timestamp);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void update() throws Exception {
		final Task task = Task.builder().id(1L).descripcion("Updated Task").fechaCreacion(current).vigente(true).build();

		Mockito.when(taskService.update(Mockito.any())).thenReturn(Boolean.TRUE);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/task").
			contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(task));

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final long timestamp = mapper.readTree(result.getResponse().getContentAsString()).get("timestamp").asLong();

		final String expected = String.format("{\"code\":200,\"data\":null,\"status\":\"OK\","
			+ "\"message\":\"Updated successfully!\",\"timestamp\":%d}", timestamp);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void delete() throws Exception {
		Mockito.when(taskService.deleteById(Mockito.anyLong())).thenReturn(Boolean.TRUE);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/task/1");

		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		log.info(result.getResponse().getContentAsString());

		final long timestamp = mapper.readTree(result.getResponse().getContentAsString()).get("timestamp").asLong();

		final String expected = String.format("{\"code\":200,\"data\":null,\"status\":\"OK\","
			+ "\"message\":\"Deteled successfully!\",\"timestamp\":%d}", timestamp);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
