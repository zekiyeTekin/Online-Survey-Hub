package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.dto.UserDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.mapper.UserMapper;
import com.zekiyetekin.surveyhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");
    }

    @Test
    void testGetUserById_Success(){
        //Arrange
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Test");
        user.setMail("test@test.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        //Act:
        ResponseModel<User> response = userService.getUserById(userId);

        //Assert:
        assertNotNull(response);
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals(user, response.getData());
    }

    @Test
    void testGetUserById_Exception(){
        //Arrange:
        Integer userId = 2;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //Act:
        ResponseModel<User> response = userService.getUserById(userId);

        //Assert:
        assertNotNull(response);
        assertEquals(ResponseStatusEnum.NOT_FOUND.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DATA_NOT_FOUND, response.getMessage());
        assertFalse(response.getSuccess());
        assertNull(response.getData());
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setPassword("newPassword");
        userDto.setAge(25);
        userDto.setJob("Engineer");
        userDto.setName("Test User");

        User user = new User();
        user.setId(1);
        user.setPassword("encodedPassword");
        user.setAge(25);
        user.setJob("Engineer");
        user.setName("Test User");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.encode("newPassword")).thenReturn("encodedPassword");
        when(userMapper.toDto(user)).thenReturn(userDto);

        // Act
        ResponseModel<UserDto> response = userService.update(userDto);

        // Assert
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.UPDATED_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals("Test User", response.getData().getName());
    }

    @Test
    void testUpdate_Exception() {
        //Arrange
        Integer userId = 2;

        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setPassword("newPassword");
        userDto.setAge(22);
        userDto.setJob("Engineer");
        userDto.setName("Test User");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //Act:
        ResponseModel<UserDto> response = userService.update(userDto);

        // Assert
        assertEquals(ResponseStatusEnum.NOT_FOUND.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.UPDATED_ERROR, response.getMessage());
        assertFalse(response.getSuccess());
        assertEquals(userDto,response.getData());
    }




}
