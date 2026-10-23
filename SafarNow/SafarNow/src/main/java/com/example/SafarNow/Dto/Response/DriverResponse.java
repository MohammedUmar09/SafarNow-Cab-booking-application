package com.example.SafarNow.Dto.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DriverResponse {

    private int driverId;

    private String name;

    private int age;

    private String emailId;
}
