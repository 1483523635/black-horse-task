package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.service.entity.OrderContractEntity;
import com.github.tomakehurst.wiremock.WireMockServer;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ContextConfiguration(classes = { WireMockConfig.class })
class TripHttpClientTest {

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private TripHttpClient tripHttpClient;

    @BeforeEach
    void setUp() throws IOException {
        TripServiceMock.mockBookingFailed(wireMockServer);
        TripServiceMock.mockBookingSuccess(wireMockServer);
    }

    @Test
    void should_return_booking_success_response() {
        final BookingTripResponse bookingTripResponse = tripHttpClient.bookingTrips("1", BookingTripRequest.fromEntity(new OrderContractEntity()));
        assertEquals("200", bookingTripResponse.getStatus());
        assertEquals("订票成功", bookingTripResponse.getMessage());
    }
    @Test
    void should_return_booking_failed_response() {
        final BookingTripResponse bookingTripResponse = tripHttpClient.bookingTrips("2", BookingTripRequest.fromEntity(new OrderContractEntity()));
        assertEquals("400", bookingTripResponse.getStatus());
        assertEquals("订票失败. 票卖完了", bookingTripResponse.getMessage());
    }
}