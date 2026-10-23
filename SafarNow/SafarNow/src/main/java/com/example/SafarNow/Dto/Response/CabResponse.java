package com.example.SafarNow.Dto.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CabResponse {
    private String cabNumber;

    private String cabmodel;

    private double perKmRate;

    private boolean available;

    private DriverResponse driverResponse;
}
