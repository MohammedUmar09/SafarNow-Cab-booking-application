package com.example.SafarNow.Service;

import com.example.SafarNow.Dto.Request.CabRequest;
import com.example.SafarNow.Dto.Response.CabResponse;
import com.example.SafarNow.Exception.DriverNotFound;
import com.example.SafarNow.Model.Cab;
import com.example.SafarNow.Model.Driver;
import com.example.SafarNow.Repository.CabRepository;
import com.example.SafarNow.Repository.DriverRepository;
import com.example.SafarNow.Transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabService {

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    public CabResponse registerCab(CabRequest cabRequest, int driverId) {

        Optional<Driver> OptionalDriver = driverRepository.findById(driverId);

        if(OptionalDriver.isEmpty()){
            throw new DriverNotFound("Invalid Driver Id!!");
        }

        Driver driver = OptionalDriver.get();

        //cabRequestDto-->cabEntity
        Cab cab = CabTransformer.cabRequestToCab(cabRequest);

        driver.setCab(cab);

        Driver savedDriver = driverRepository.save(driver);//Cascade=driver-parent,child-cab

        return CabTransformer.cabToCabResponse(savedDriver.getCab(), savedDriver);
    }
}
