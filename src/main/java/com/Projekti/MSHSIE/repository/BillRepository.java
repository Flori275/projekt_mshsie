package com.Projekti.MSHSIE.repository;

import com.Projekti.MSHSIE.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

}
