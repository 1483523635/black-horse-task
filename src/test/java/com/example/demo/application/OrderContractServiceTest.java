package com.example.demo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.service.BookingTripRequest;
import com.example.demo.service.BookingTripResponse;
import com.example.demo.service.OrderContractRepository;
import com.example.demo.service.TripHttpClient;
import com.example.demo.service.entity.OrderContractEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class OrderContractServiceTest {

    @Test
    void should_return_contract_not_found_error_message_when_contract_not_exits() {
        final String contractId = "1";
        final OrderContractRepository orderContractRepository = mock(OrderContractRepository.class);
        when(orderContractRepository.findById(1L)).thenReturn(Optional.empty());

        final OrderContractService orderContractService = new OrderContractService(orderContractRepository, null);

        final BookingTicketDto bookingTicketDto = orderContractService.bookingTicket(contractId);
        assertEquals("订票失败:订单合同没找到", bookingTicketDto.getResult());
    }

    @Test
    void should_return_booking_ticket_success_when_ticket_booking_from_ticket_service_success() {
        final String contractId = "1";
        final OrderContractRepository orderContractRepository = mock(OrderContractRepository.class);
        final LocalDateTime now = LocalDateTime.now();
        final OrderContractEntity orderContractEntity = new OrderContractEntity(1L, now, now, "TW-XX", "任你行李四", "北京西", "西安北", "G8088", now);
        when(orderContractRepository.findById(1L)).thenReturn(Optional.of(orderContractEntity));
        final TripHttpClient tripHttpClient = mock(TripHttpClient.class);
        when(tripHttpClient.bookingTrips(contractId, BookingTripRequest.fromEntity(orderContractEntity))).thenReturn(new BookingTripResponse("200", "订票成功"));

        final OrderContractService orderContractService = new OrderContractService(orderContractRepository, tripHttpClient);

        final BookingTicketDto bookingTicketDto = orderContractService.bookingTicket(contractId);
        assertEquals("订票成功", bookingTicketDto.getResult());
    }

    @Test
    void should_return_booking_ticket_failed_when_ticket_booking_from_ticket_service_failed() {
        final String contractId = "1";
        final OrderContractRepository orderContractRepository = mock(OrderContractRepository.class);
        final LocalDateTime now = LocalDateTime.now();
        final OrderContractEntity orderContractEntity = new OrderContractEntity(1L, now, now, "TW-XX", "任你行李四", "北京西", "西安北", "G8088", now);
        when(orderContractRepository.findById(1L)).thenReturn(Optional.of(orderContractEntity));
        final TripHttpClient tripHttpClient = mock(TripHttpClient.class);
        when(tripHttpClient.bookingTrips(contractId, BookingTripRequest.fromEntity(orderContractEntity))).thenReturn(new BookingTripResponse("400", "订票失败，票卖完了"));

        final OrderContractService orderContractService = new OrderContractService(orderContractRepository, tripHttpClient);

        final BookingTicketDto bookingTicketDto = orderContractService.bookingTicket(contractId);
        assertEquals("订票失败，票卖完了", bookingTicketDto.getResult());
    }
}