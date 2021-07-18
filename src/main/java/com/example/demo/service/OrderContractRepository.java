package com.example.demo.service;

import com.example.demo.service.entity.OrderContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderContractRepository extends JpaRepository<OrderContractEntity, Long> {

}
