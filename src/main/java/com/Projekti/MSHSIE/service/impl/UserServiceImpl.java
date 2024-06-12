package com.Projekti.MSHSIE.service.impl;

import com.Projekti.MSHSIE.dto.car.CarDTO;
import com.Projekti.MSHSIE.dto.user.UserDTO;
import com.Projekti.MSHSIE.dto.user.UserUpdateDTO;
import com.Projekti.MSHSIE.entity.Role;
import com.Projekti.MSHSIE.entity.User;
import com.Projekti.MSHSIE.exceptions.NotFoundException;
import com.Projekti.MSHSIE.mapper.CarMapper;
import com.Projekti.MSHSIE.mapper.user.UserMapper;
import com.Projekti.MSHSIE.repository.CarRepository;
import com.Projekti.MSHSIE.repository.UserRepository;
import com.Projekti.MSHSIE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User findById(Integer id) {
    return userRepository
            .findById(id)
            .orElseThrow(
                    () -> new NotFoundException(format("User not found")));
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO, String userRole) {
        User u = UserMapper.toEntity(userDTO);
        u.setUserRole(userRole != null ? Role.fromValue(userRole) : Role.CUSTOMER);
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u = userRepository.save(u);
        if (!userDTO.getCarDTOS().isEmpty()){
        List<CarDTO> cars = new ArrayList<>(userDTO.getCarDTOS());
        for (CarDTO c : cars) {
            carRepository.save(CarMapper.toAddforEntity(c, u));
        } }
        return UserMapper.toDto(u);
        }


    @Override
    public UserUpdateDTO updateUser(Integer id, UserUpdateDTO upd) {
        User u = userRepository
                .findById(id).orElseThrow(
                        () -> new NotFoundException(
                                format("User with id %s not found",id)));
        u = userRepository.save(UserMapper.buildUpdateUser(upd,u));
        return UserMapper.toUpdateDto(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("User with email %s not found",email)));
    }
}
