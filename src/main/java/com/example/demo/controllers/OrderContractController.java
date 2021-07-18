package com.example.demo.controllers;

import com.example.demo.application.BookingTicketDto;
import com.example.demo.application.OrderContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-contracts")
@RequiredArgsConstructor
public class OrderContractController {

    private final OrderContractService orderContractService;

    @PostMapping("/{id}/booking-ticket-requests")
    public ResponseEntity<BookingTicketDto> createBookingTicketRequest(@PathVariable("id") String contractId) {
        BookingTicketDto result = orderContractService.bookingTicket(contractId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
