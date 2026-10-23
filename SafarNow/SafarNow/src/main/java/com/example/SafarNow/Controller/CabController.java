package com.example.SafarNow.Controller;

import com.example.SafarNow.Dto.Request.CabRequest;
import com.example.SafarNow.Dto.Response.CabResponse;
import com.example.SafarNow.Service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @PostMapping("/register-cab/{driverId}")
    public CabResponse registerCab(@RequestBody CabRequest cabRequest,
                                   @PathVariable("driverId") int driverId){
        return cabService.registerCab(cabRequest, driverId);
    }
}
