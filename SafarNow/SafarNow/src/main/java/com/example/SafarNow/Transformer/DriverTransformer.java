package com.example.SafarNow.Transformer;

import com.example.SafarNow.Dto.Request.DriverRequest;
import com.example.SafarNow.Dto.Response.DriverResponse;
import com.example.SafarNow.Model.Driver;

public class DriverTransformer {
    //RequestDto-->Entity
    public static Driver driverRequestToDriver(DriverRequest driverRequest){

        return Driver.builder()
                .name(driverRequest.getName())
                .age(driverRequest.getAge())
                .emailId(driverRequest.getEmailId()).build();
    }

    public static DriverResponse driverToDriverResponse(Driver driver){

        return DriverResponse.builder()
                    .driverId(driver.getDriverId())
                    .name(driver.getName())
                    .age(driver.getAge())
                    .emailId(driver.getEmailId()).build();
    }
}

