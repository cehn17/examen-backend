package com.examen.backend.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import com.examen.backend.entity.User;
import com.examen.backend.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRestController.class)
class UserRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("removal")
	@MockBean
	private IUserService userService;

	@InjectMocks
	@Autowired
	private UserRestController controller;

	@Mock
	BindingResult result;

	private List<User> userList;

	@BeforeEach
	void setUp() {
		this.userList = new ArrayList<>();
		this.userList.add(new User(1L, "user1@gmail.com", "Name1", "LastName1"));
		this.userList.add(new User(2L, "user2@gmail.com", "Name1", "LastName1"));
		this.userList.add(new User(3L, "user3@gmail.com", "Name1", "LastName1"));
	}

	@DisplayName("Test Find all users - OK")
	@Test
	void TestFindAllUsersOK() throws Exception {

		when(userService.findAll()).thenReturn(userList);

		this.mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(userList.size())));
	}

	@DisplayName("Test Create user - Created")
	@Test
	void TestSaveUserReturn201() throws Exception {

		User user = new User(1L, "test@app.com", "Pepe", "Argento");

		mockMvc.perform(post("/users", user, result)
				.param("sendWelcomeMail", "true")
				.content(objectMapper.writeValueAsString(user))
				.contentType("application/json"))
				.andExpect(status().isCreated());

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userService, times(1)).save(userCaptor.capture());
		assertThat(userCaptor.getValue().getFirstName()).isEqualTo("Pepe");
	    assertThat(userCaptor.getValue().getEmail()).isEqualTo("test@app.com");
	}

	@DisplayName("Test Create user - BadRequest")
	@Test
	void TestSaveUserReturn400() throws Exception {

		User user = new User(1L, "email", "Pepe", "Argento");

		mockMvc.perform(post("/users", user, result)
				.param("sendWelcomeMail", "true")
				.content(objectMapper.writeValueAsString(user))
				.contentType("application/json"))
				.andExpect(status().isBadRequest());
	}
	
	@DisplayName("Test Find User by ID - NOT_FOUND")
	@Test
    void TestFindUserByIdNotFound() throws Exception {
        Long userId = 1L;
        when(userService.findById(userId)).thenReturn(null);

        this.mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isNotFound());

    }
	
	@DisplayName("Test Find user by id - OK")
	@Test
    void TestFindUserByIdReturn200() throws Exception {
        Long userId = 1L;
        User user = new User(1L, "test@app.com", "Pepe", "Argento");
        when(userService.findById(userId)).thenReturn(user);

        this.mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())));

    }
	
	@DisplayName("Test Delete User - OK")
	@Test
    void TestDeleteUserOK() throws Exception {
        Long userId = 1L;
        User user = new User(userId, "test@app.com", "Pepe", "Argento");
        
        when(userService.findById(userId)).thenReturn(user);
        doNothing().when(userService).delete(user.getId());

        this.mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isOk());

    }

}
