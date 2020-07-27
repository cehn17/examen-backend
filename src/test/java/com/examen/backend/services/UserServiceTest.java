package com.examen.backend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.examen.backend.dao.IUserDao;
import com.examen.backend.entity.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private IUserDao userDao; 
	
	@InjectMocks
    private UserServiceImpl userService;
	
	
	@DisplayName("Find All Users OK")
	@Test
	public void testUserFindAllOK() {
		List<User> datos = new ArrayList<>();
		datos.add(new User(1L, "test@mail.com","test1","testAp1"));
		datos.add(new User(2L, "test2@mail.com","test2","testAp2"));
		datos.add(new User(3L, "test3@mail.com","test3","testAp3"));
        
		when(this.userDao.findAll()).thenReturn(datos);
		List<User> expected = userService.findAll();
		
		assertEquals(expected, datos);
        assertEquals(this.userService.findAll().size(), 3 );
	}
	
	@DisplayName("Find By ID User OK")
	@Test
    void findUserByIdOK(){
        final Long id = 1L;
        final User user = new User(1L, "test@mail.com","Pepe","Argento");

        when(userDao.findById(id)).thenReturn(Optional.of(user));

        final User expected  = userService.findById(id);

        assertThat(expected).isNotNull();

    }
	
	
	@DisplayName("Save User OK")
	@Test
    void saveTestOK(){
        final User user = new User(1L, "test@mail.com","Pepe","Argento");

        when(userDao.save(user)).thenReturn(user);

        final User expected  = userService.save(user);

        assertThat(expected).isNotNull();

    }
	
	@DisplayName("Delete User OK")
	@Test
    void shouldBeDeleteOK() {
        final Long userId=1L;
        userService.delete(userId);
        userService.delete(userId);
        verify(userDao, times(2)).deleteById(userId);
    }

}
