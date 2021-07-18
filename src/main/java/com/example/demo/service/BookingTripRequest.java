package com.example.demo.service;

import com.example.demo.service.entity.OrderContractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingTripRequest {

    private String from;
    private String to;
    private String trainNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;

    public static BookingTripRequest fromEntity(OrderContractEntity orderContractEntity) {
        return new BookingTripRequest(orderContractEntity.getFrom(), orderContractEntity.getTo(), orderContractEntity.getTrainNo(),
            orderContractEntity.getTime());
    }
}
