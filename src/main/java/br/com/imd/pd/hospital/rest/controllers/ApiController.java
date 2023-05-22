package br.com.imd.pd.hospital.rest.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imd.pd.hospital.models.Hospital;
import br.com.imd.pd.hospital.models.Location;
import br.com.imd.pd.hospital.rest.dto.LocationRequest;
import br.com.imd.pd.hospital.services.HospitalLocatorServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiController {
    private HospitalLocatorServiceImpl hospitalLocatorService;
    private Hospital hospital;

    public ApiController() {
        this.hospitalLocatorService = new HospitalLocatorServiceImpl();
    }

    @PostMapping("/location")
    public Hospital getLocation(@RequestBody LocationRequest locationRequest) throws Exception {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();
        Location location = new Location(latitude, longitude);
        setHospital(hospitalLocatorService.findNearestHospital(location));
        return getHospital();
    }
   
    public void setHospital(Hospital hospital){
            this.hospital = hospital;
    }

    public Hospital getHospital(){
        return this.hospital ;
    }
}
