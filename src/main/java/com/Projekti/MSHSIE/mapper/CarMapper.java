package com.Projekti.MSHSIE.mapper;

import com.Projekti.MSHSIE.dto.car.CarDTO;
import com.Projekti.MSHSIE.entity.Car;
import com.Projekti.MSHSIE.entity.User;

public class CarMapper {

    public static CarDTO toDto(Car car) {

        return CarDTO.builder()
                .id(car.getId())
                .chassis(car.getChassis())
                .make(car.getMake())
                .enginePower(car.getEnginePower())
                .producedAt(car.getProducedAt())
                .createdAt(car.getCreatedAt())
                .build();
    }


    public static Car toEntity(CarDTO carDTO) {
        return Car.builder()
                .id(carDTO.getId())
                .make(carDTO.getMake())
                .chassis(carDTO.getChassis())
                .enginePower(carDTO.getEnginePower())
                .producedAt(carDTO.getProducedAt())
                .createdAt(carDTO.getCreatedAt())
                .build();
    }

    public static Car toAddforEntity(CarDTO carDTO, User u) {
        return Car.builder()
                .id(carDTO.getId())
                .make(carDTO.getMake())
                .chassis(carDTO.getChassis())
                .enginePower(carDTO.getEnginePower())
                .producedAt(carDTO.getProducedAt())
                .createdAt(carDTO.getCreatedAt())
                .user(u)
                .build();

    }
}
