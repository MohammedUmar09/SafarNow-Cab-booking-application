package com.example.SafarNow.Service;

import com.example.SafarNow.Dto.Request.DriverRequest;
import com.example.SafarNow.Dto.Response.DriverResponse;
import com.example.SafarNow.Model.Driver;
import com.example.SafarNow.Repository.DriverRepository;
import com.example.SafarNow.Transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public DriverResponse addDriver(DriverRequest driverRequest) {
        //driverRequestDto-->Entity
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);

        //driverEntity-->driverResponseDto
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
