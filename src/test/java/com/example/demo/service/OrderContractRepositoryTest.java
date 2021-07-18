package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.demo.service.entity.OrderContractEntity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class OrderContractRepositoryTest {


    @Autowired
    private OrderContractRepository orderContractRepository;

    @Test
    void should_mapping_entity_success() {
        LocalDateTime now = LocalDateTime.now();
        final OrderContractEntity orderContractEntity = new OrderContractEntity(1L, now, now, "TW-XX", "任你行李四", "北京西", "西安北", "G8088", now);
        final OrderContractEntity result = orderContractRepository.save(orderContractEntity);
        assertNotNull(result);
        assertSame(1L, result.getId());
        assertEquals("任你行李四", result.getCreateUser());
        assertEquals("北京西",result.getFrom());
        assertEquals("西安北",result.getTo());
        assertEquals("G8088",result.getTrainNo());
    }
}