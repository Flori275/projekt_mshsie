package com.Projekti.MSHSIE.mapper;

import com.Projekti.MSHSIE.dto.bill.BillDTO;
import com.Projekti.MSHSIE.entity.Bill;
import com.Projekti.MSHSIE.mapper.user.UserMapper;

public class BillMapper {

    public static BillDTO toDto(Bill bill){
        return BillDTO.builder()
                .id(bill.getId())
                .userDTO(bill.getUser()!=null? UserMapper.toDto(bill.getUser()):null)
                .price(bill.getPrice())
                .status(bill.getStatus())
                .build();
    }



    public static Bill toEntity(BillDTO billDTO){
        return Bill.builder()
                .id(billDTO.getId())
                .price(billDTO.getPrice())
                .user(billDTO.getUserDTO()!=null?UserMapper.toEntity(billDTO.getUserDTO()):null)
                .status(billDTO.getStatus())
                .build();
    }

}
