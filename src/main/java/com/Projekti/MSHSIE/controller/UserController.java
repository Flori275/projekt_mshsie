package com.Projekti.MSHSIE.controller;

import com.Projekti.MSHSIE.dto.user.UserDTO;
import com.Projekti.MSHSIE.dto.user.UserUpdateDTO;
import com.Projekti.MSHSIE.entity.User;
import com.Projekti.MSHSIE.mapper.user.UserMapper;
import com.Projekti.MSHSIE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register/{userRole}")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO, @PathVariable String userRole){
       UserDTO dto = userService.registerUser(userDTO,userRole);
       return ResponseEntity.ok(dto);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@RequestBody @Valid UserUpdateDTO userDTO, @PathVariable Integer id){
        User u = userService.findById(id);
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Integer id){
        User u = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(u));
    }
}



