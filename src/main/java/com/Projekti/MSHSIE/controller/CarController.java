package com.Projekti.MSHSIE.controller;

import com.Projekti.MSHSIE.dto.car.CarDTO;
import com.Projekti.MSHSIE.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cars")
@Validated
public class CarController {


    @Autowired
    private CarService carService;


    @PostMapping("/user/addCar")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO){
        return ResponseEntity.ok(carService.addCar(carDTO));
    }



    @GetMapping("/admin/car/{id}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable Integer id){
        return ResponseEntity.ok(carService.findCarById(id));
}


    @GetMapping("/admin/userCar/{userId}")
    public ResponseEntity<List<CarDTO>> findCarByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(carService.findCarByUserId(userId));
}

    @PostMapping("/user/car/{userId}")
    public ResponseEntity<Void> addCarByUserId(@RequestBody @Valid CarDTO carDTO, @PathVariable Integer userId){
        return ResponseEntity.ok(carService.addCarToUser(carDTO,userId));
}

     @DeleteMapping("/admin/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id){
        return ResponseEntity.ok(carService.deleteCar(id));
}

}
