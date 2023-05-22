package br.com.imd.pd.hospital.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.com.imd.pd.hospital.models.Hospital;
import br.com.imd.pd.hospital.models.Location;

public class HospitalLocatorImpl implements HospitalLocator {
    private List<Hospital> hospitals;
    private int chamado;

    public HospitalLocatorImpl() throws RemoteException {
        super();

        hospitals = new ArrayList<>();
        hospitals.add(new Hospital("Hospital Maranhão", 2, new Location(-5.6, -45.3)));
        hospitals.add(new Hospital("Hospital Bahia", 2, new Location(-12.8, -41.5)));
        hospitals.add(new Hospital("Hospital Pernambuco", 2, new Location(-8.7, -38.2)));
        hospitals.add(new Hospital("Hospital Rio Grande", 2, new Location(-6.0, -36.7)));

        chamado = 0;
    }

    public Hospital findNearestHospital(Location location) throws Exception {
        Hospital nearestHospital = null;
        double nearestDistance = Double.MAX_VALUE;

        System.out.println("Chamado número " + chamado++ + " --> Local(" + location.getLatitude() + ", " + location.getLongitude() + ")");

        List<Hospital> auxHospitals = new ArrayList<>(this.hospitals);

        for (Hospital hospital : auxHospitals) {
            double distance = location.distance(hospital.getLocation());
            if (distance < nearestDistance) {
                nearestHospital = hospital;
                nearestDistance = distance;
            }
        }

        if(!nearestHospital.isAvaliable()) {
            auxHospitals.remove(nearestHospital);
            return findNearestHospital(location, auxHospitals);
        }

        int index = this.hospitals.indexOf(nearestHospital);
        this.hospitals.get(index).occupyVacancy();

        return nearestHospital;
    }

    private Hospital findNearestHospital(Location location, List<Hospital> hospitals) throws Exception {
        
        if(hospitals.isEmpty()) {
            throw new Exception("Colapso no sistema de saúde. Todos os hospitais estão sem vagas disponíveis.");
            
        }

        Hospital nearestHospital = null;
        double nearestDistance = Double.MAX_VALUE;

        List<Hospital> auxHospitals = new ArrayList<>(hospitals);

        for (Hospital hospital : auxHospitals) {
            double distance = location.distance(hospital.getLocation());
            if (distance < nearestDistance) {
                nearestHospital = hospital;
                nearestDistance = distance;
            }
        }

        if(!nearestHospital.isAvaliable()) {
            auxHospitals.remove(nearestHospital);
            return findNearestHospital(location, auxHospitals);
        }

        int index = this.hospitals.indexOf(nearestHospital);
        this.hospitals.get(index).occupyVacancy();
        return nearestHospital;
    }

    
}
