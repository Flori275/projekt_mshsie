package com.Projekti.MSHSIE.mapper.user;

import com.Projekti.MSHSIE.dto.user.UserDTO;
import com.Projekti.MSHSIE.dto.user.UserUpdateDTO;
import com.Projekti.MSHSIE.entity.User;
import com.Projekti.MSHSIE.mapper.BillMapper;
import com.Projekti.MSHSIE.mapper.CarMapper;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .surname(u.getSurname())
                .carDTOS(u.getCarList()!=null? u.getCarList().stream().map(CarMapper::toDto).collect(Collectors.toList()):null)
                .build();
    }

    public static User toEntity(UserDTO userDTO) {

        User u =  User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .carList(userDTO.getCarDTOS()!=null?userDTO.getCarDTOS().stream().map(CarMapper::toEntity).collect(Collectors.toList()) : null)
                .billList(userDTO.getBillDTOS()!=null?userDTO.getBillDTOS().stream().map(BillMapper::toEntity).collect(Collectors.toList()) : null)
                .build();
        return u;
    }
   public static UserUpdateDTO toUpdateDto(User u){
        return UserUpdateDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .surname(u.getSurname())
                .email(u.getEmail())
                .build();
   }

   public static User buildUpdateUser(UserUpdateDTO userUpdateDTO,User u){
        u.setName(userUpdateDTO.getName());
        u.setSurname(userUpdateDTO.getSurname());
        u.setEmail(userUpdateDTO.getEmail());
            return u;

   }
}
