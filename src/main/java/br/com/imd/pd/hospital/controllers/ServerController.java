package br.com.imd.pd.hospital.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imd.pd.hospital.models.Hospital;
import br.com.imd.pd.hospital.models.Location;
import br.com.imd.pd.hospital.models.LocationRequest;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/server")

public class ServerController {
    private HospitalLocator hospitalLocator;
    private Hospital hospital;

    public ServerController() throws RemoteException {
        this.hospitalLocator = new HospitalLocator();
        
       
    }
    public void setHospital(Hospital hospital){
            this.hospital = hospital;
    }

    public Hospital getHospital(){
        return this.hospital ;
}


    @PostMapping("/location")
    public Hospital getLocation(@RequestBody LocationRequest locationRequest) throws RemoteException {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();
        Location location = new Location(latitude, longitude);
        setHospital(hospitalLocator.findNearestHospital(location));
        return getHospital();
    }
   
}
