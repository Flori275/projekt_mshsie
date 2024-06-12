package com.Projekti.MSHSIE.service;

import com.Projekti.MSHSIE.dto.car.CarDTO;


import java.util.List;

public interface CarService {

    CarDTO addCar(CarDTO carDTO);

    CarDTO findCarById(Integer id);

    List<CarDTO> findCarByUserId(Integer userId);

    Void addCarToUser(CarDTO carDto,Integer userId);

    Void deleteCar(Integer id);

}
