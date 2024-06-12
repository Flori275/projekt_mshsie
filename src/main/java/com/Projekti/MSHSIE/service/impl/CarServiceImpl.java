package com.Projekti.MSHSIE.service.impl;

import com.Projekti.MSHSIE.dto.car.CarDTO;
import com.Projekti.MSHSIE.entity.Car;
import com.Projekti.MSHSIE.entity.User;
import com.Projekti.MSHSIE.exceptions.NotFoundException;
import com.Projekti.MSHSIE.mapper.CarMapper;
import com.Projekti.MSHSIE.repository.CarRepository;
import com.Projekti.MSHSIE.repository.UserRepository;
import com.Projekti.MSHSIE.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CarDTO addCar(CarDTO carDTO) {
        Car c = CarMapper.toEntity(carDTO);
        return CarMapper.toDto(carRepository.save(c));
    }

    @Override
    public CarDTO findCarById(Integer id) {
       Car c = carRepository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("Car with id %s not found",id)));
       return CarMapper.toDto(c);
    }

    @Override
    public List<CarDTO> findCarByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s does not exist",userId)));
        List<CarDTO> cars = user.getCarList().stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
        return cars;

    }

    @Override
    public Void addCarToUser(CarDTO carDto, Integer userId) {

        User u = userRepository.findById(userId)
                .orElseThrow( () -> new NotFoundException(String.format("User with id %d not found")));
        carRepository.save(CarMapper.toAddforEntity(carDto,u));
        return null;

    }

    public Void deleteCar(Integer id){

       Car c =  carRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Car with id %s not found",id)));
        carRepository.deleteById(id);
        return null;
    }

}
