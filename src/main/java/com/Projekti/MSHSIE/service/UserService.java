package com.Projekti.MSHSIE.service;

import com.Projekti.MSHSIE.dto.user.UserDTO;
import com.Projekti.MSHSIE.dto.user.UserUpdateDTO;
import com.Projekti.MSHSIE.entity.User;

public interface UserService {

    User findById(Integer id);

    UserDTO registerUser(UserDTO userDTO,String userRole);

    UserUpdateDTO updateUser(Integer id,UserUpdateDTO upd);


}
