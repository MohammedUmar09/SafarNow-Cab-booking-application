package com.example.SafarNow.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingRequest {

    String pickUp;

    String destination;

    double tripDistanceInKm;
}
