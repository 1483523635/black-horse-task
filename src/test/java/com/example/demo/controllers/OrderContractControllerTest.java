package com.example.demo.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.example.demo.application.OrderContractService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderContractControllerTest {

    @Test
    void should_return_404_when_contract_id_not_found() {

        final OrderContractService orderContractService = mock(OrderContractService.class);
        when(orderContractService.bookingTicket("9")).thenReturn(null);
        final OrderContractController orderContractController = new OrderContractController(orderContractService);
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .standaloneSetup(orderContractController)
            .when()
            .post("/order-contracts/9/booking-ticket-requests")
            .then()
            .status(HttpStatus.NOT_FOUND);
    }
}