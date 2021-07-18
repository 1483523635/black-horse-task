package com.example.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class TripServiceMock {


    public static void mockBookingSuccess(WireMockServer mockService) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final String responseBody = mapper.writeValueAsString(new BookingTripResponse("200", "订票成功", "001"));
        mockService.stubFor(WireMock.post(WireMock.urlEqualTo("/trip-service/booking-contracts/1/booking-requests"))
                                    .willReturn(WireMock.aResponse()
                                                        .withStatus(HttpStatus.OK.value())
                                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                                        .withBody(responseBody)));
    }
    public static void mockBookingFailed(WireMockServer mockService) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final String responseBody = mapper.writeValueAsString(new BookingTripResponse("400", "订票失败. 票卖完了", null));
        mockService.stubFor(WireMock.post(WireMock.urlEqualTo("/trip-service/booking-contracts/2/booking-requests"))
                                    .willReturn(WireMock.aResponse()
                                                        .withStatus(HttpStatus.OK.value())
                                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                                        .withBody(responseBody)));
    }
    public static void mockBookingSystemError(WireMockServer mockService) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final String responseBody = mapper.writeValueAsString(new BookingTripResponse("400", "系统错误请联系管理员", null));
        mockService.stubFor(WireMock.post(WireMock.urlEqualTo("/trip-service/booking-contracts/3/booking-requests"))
                                    .willReturn(WireMock.aResponse()
                                                        .withStatus(HttpStatus.OK.value())
                                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                                        .withBody(responseBody)));
    }
}
