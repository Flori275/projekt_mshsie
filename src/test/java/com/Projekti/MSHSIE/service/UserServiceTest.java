package com.Projekti.MSHSIE.service;


import com.Projekti.MSHSIE.BaseTest;
import com.Projekti.MSHSIE.dto.user.UserDTO;
import com.Projekti.MSHSIE.dto.user.UserUpdateDTO;
import com.Projekti.MSHSIE.entity.User;
import com.Projekti.MSHSIE.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;


@SpringBootTest
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService toTest;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void test_findById_ok() {
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User out = toTest.findById(1);
        Assertions.assertNotNull(out);
    }


    @Test
    public void test_updateUser_ok() {
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.any());
        Mockito.doReturn(new User()).when(userRepository).save(Mockito.any());
        UserUpdateDTO out = toTest.updateUser(Mockito.anyInt(), new UserUpdateDTO());
        Assertions.assertNotNull(out);
    }


}
