package com.example.demo.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.example.demo.application.BookingTicketDto;
import com.example.demo.application.OrderContractService;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

class OrderContractControllerTest {

    @Test
    void should_return_booking_failed_when_contract_id_not_found() throws IOException {
        final OrderContractService orderContractService = mock(OrderContractService.class);
        when(orderContractService.bookingTicket("9")).thenReturn(new BookingTicketDto("预约失败,原因：票卖完了"));
        final OrderContractController orderContractController = new OrderContractController(orderContractService);

        final String bodyStr = given().contentType(APPLICATION_JSON_VALUE).standaloneSetup(orderContractController).when()
                                .post("/order-contracts/9/booking-ticket-requests").getBody().asString();

        ObjectMapper mapper = new ObjectMapper();
        final BookingTicketDto bookingTicketDto = mapper.readerFor(BookingTicketDto.class).readValue(bodyStr);
        assertEquals("预约失败,原因：票卖完了", bookingTicketDto.getResult());

    }

    @Test
    void should_return_booking_success_message_when_booking_ticket_success() throws IOException {
        final OrderContractService orderContractService = mock(OrderContractService.class);
        when(orderContractService.bookingTicket("9")).thenReturn(new BookingTicketDto("预约成功"));
        final OrderContractController orderContractController = new OrderContractController(orderContractService);

        final String bodyStr = given().contentType(APPLICATION_JSON_VALUE).standaloneSetup(orderContractController).when()
                                      .post("/order-contracts/9/booking-ticket-requests").getBody().asString();

        ObjectMapper mapper = new ObjectMapper();
        final BookingTicketDto bookingTicketDto = mapper.readerFor(BookingTicketDto.class).readValue(bodyStr);
        assertEquals("预约成功", bookingTicketDto.getResult());
    }
}