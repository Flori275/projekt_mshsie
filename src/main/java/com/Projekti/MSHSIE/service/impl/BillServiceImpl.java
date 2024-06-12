package com.Projekti.MSHSIE.service.impl;


import com.Projekti.MSHSIE.dto.bill.BillDTO;
import com.Projekti.MSHSIE.dto.car.CarDTO;
import com.Projekti.MSHSIE.entity.Bill;
import com.Projekti.MSHSIE.entity.Car;
import com.Projekti.MSHSIE.entity.User;
import com.Projekti.MSHSIE.exceptions.BadRequestException;
import com.Projekti.MSHSIE.exceptions.NotFoundException;
import com.Projekti.MSHSIE.mapper.BillMapper;
import com.Projekti.MSHSIE.repository.BillRepository;
import com.Projekti.MSHSIE.repository.CarRepository;
import com.Projekti.MSHSIE.repository.UserRepository;
import com.Projekti.MSHSIE.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public BillDTO addBill(Integer userId) {

        Bill b = new Bill();
        User u = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", userId)));
        List<Car> cars = u.getCarList();
        double totalPrice = 0;
        for (Car car : cars) {
                if (car.getProducedAt().getYear() <= 2009) {
                    totalPrice += 600.00;
                }
                else {
                    totalPrice += car.getEnginePower() * 0.7;
                }
            }

            b.setPrice(totalPrice);
            b.setStatus(false);
            b.setUser(u);
            billRepository.save(b);
            return BillMapper.toDto(b);

    }

@Override
    public BillDTO addBillForCar(Integer userId,CarDTO carDTO){

        Bill b = new Bill();
        User u = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", userId)));
        List<Car> cars = u.getCarList();
        double totalPrice = 0;

        for (Car car : cars) {
            if (car.getChassis().trim().equalsIgnoreCase(carDTO.getChassis().trim())) {
                throw new BadRequestException("Bill for this car already exists for this user");
            }
        }

        if (carDTO.getProducedAt().getYear() <= 2009) {
            totalPrice += 600.00;
        }
        else {
            totalPrice += carDTO.getEnginePower() * 0.7;
        }

        b.setPrice(totalPrice);
        b.setStatus(false);
        b.setUser(u);
        billRepository.save(b);
        return BillMapper.toDto(b);
    }

    @Override
    public List<BillDTO> getUserBills(Integer userId){
        User u = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id %s Not Found",userId)));
        List<BillDTO> b = u.getBillList().stream().map(BillMapper::toDto).collect(Collectors.toList());
        return b;
}


    @Override
    public BillDTO findBill(Integer id) {

        Bill b = billRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Bill with id %s not found", id)));
        return BillMapper.toDto(b);
    }

    @Override
    public Void payBill(Integer id) {

        Bill b = billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Bill not found")));
        if ( b.getStatus() != true){
            b.setStatus(true);
            billRepository.save(b);
        }

        else {
            throw new BadRequestException(String.format("Bill already paid"));
        }

        return null;
    }

    @Override
    public Void deleteBill(Integer id) {

        billRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Bill with id %d not found", id)));
        billRepository.deleteById(id);
        return null;
    }


}
