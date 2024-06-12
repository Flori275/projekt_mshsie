package com.Projekti.MSHSIE.service;

import com.Projekti.MSHSIE.dto.bill.BillDTO;
import com.Projekti.MSHSIE.dto.car.CarDTO;

import java.util.List;

public interface BillService {

    BillDTO addBill(Integer userId);
    BillDTO addBillForCar(Integer userId,CarDTO carDTO);
    BillDTO findBill(Integer id);
    List<BillDTO> getUserBills(Integer userId);
    Void  payBill(Integer id);
    Void deleteBill(Integer id);


}
