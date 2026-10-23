package com.example.SafarNow.Transformer;

import com.example.SafarNow.Dto.Request.CabRequest;
import com.example.SafarNow.Dto.Response.CabResponse;
import com.example.SafarNow.Model.Cab;
import com.example.SafarNow.Model.Driver;

public class CabTransformer {

    public static Cab cabRequestToCab(CabRequest cabRequest){

        return Cab.builder()
                .cabNumber(cabRequest.getCabNumber())
                .cabmodel(cabRequest.getCabmodel())
                .perKmRate(cabRequest.getPerKmRate())
                .available(true).build();
    }

    public static CabResponse cabToCabResponse(Cab cab, Driver driver){
        return CabResponse.builder()
                .cabNumber(cab.getCabNumber())
                .cabmodel(cab.getCabmodel())
                .perKmRate(cab.getPerKmRate())
                .available(cab.isAvailable())
                .driverResponse(DriverTransformer.driverToDriverResponse(driver)).build();
    }
}
