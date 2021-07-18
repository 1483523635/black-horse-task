package com.example.demo.application;

import com.example.demo.application.*;
import com.example.demo.service.BookingTripRequest;
import com.example.demo.service.BookingTripResponse;
import com.example.demo.service.OrderContractRepository;
import com.example.demo.service.TripHttpClient;
import com.example.demo.service.entity.OrderContractEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderContractService {

    private final OrderContractRepository orderContractRepository;
    private final TripHttpClient tripHttpClient;

    public BookingTicketDto bookingTicket(String contractId) {
        final Optional<OrderContractEntity> orderContractEntityOptional = orderContractRepository.findById(Long.parseLong(contractId));
        if (!orderContractEntityOptional.isPresent()) {
            return new BookingTicketDto("订票失败:订单合同没找到");
        }
        final OrderContractEntity orderContractEntity = orderContractEntityOptional.get();
        final BookingTripResponse bookingTripResponse = tripHttpClient.bookingTrips(contractId, BookingTripRequest.fromEntity(orderContractEntity));
        return new BookingTicketDto(bookingTripResponse.getMessage());
    }
}
