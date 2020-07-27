package com.examen.backend.controllers;


import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.examen.backend.entity.User;
import com.examen.backend.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRestController.class)
class UserRestControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private IUserService userService;
	
	@Autowired
    private ObjectMapper objectMapper;

	
	private List<User> userList;
	

	@BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(new User(1L, "user1@gmail.com", "Name1","LastName1"));
        this.userList.add(new User(2L, "user2@gmail.com", "Name1","LastName1"));
        this.userList.add(new User(3L, "user3@gmail.com", "Name1","LastName1"));   
    }
	
	@Test
    void TestAllUsers() throws Exception {

        when(userService.findAll()).thenReturn(userList);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }
	
	@Test
    void shouldReturn404WhenFindUserById() throws Exception {
        final Long userId = 1L;
        when(userService.findById(userId)).thenReturn(null);

        this.mockMvc.perform(get("/api/user/{id}", userId))
                .andExpect(status().isNotFound());
    }
	
	

}
