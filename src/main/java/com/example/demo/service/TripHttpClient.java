package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "trip-service",url = "${trip.service.url}")
public interface TripHttpClient {

    @PostMapping("/trip-service/booking-contracts/{id}/booking-requests")
    BookingTripResponse bookingTrips(@PathVariable("id") String id, @RequestBody BookingTripRequest request);
}
