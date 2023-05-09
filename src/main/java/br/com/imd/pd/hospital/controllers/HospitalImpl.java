package br.com.imd.pd.hospital.controllers;

import java.rmi.RemoteException;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.imd.pd.hospital.models.Hospital;
import br.com.imd.pd.hospital.models.Location;

public class HospitalImpl implements Hospital {
    private String name;
    private int vacancies;
    private Location location;
    private boolean avaliable;

    public HospitalImpl(String name, int vacancies, Location location) throws RemoteException {
        this.name = name;
        this.vacancies = vacancies;
        this.location = location;
        this.avaliable = (vacancies > 0);
    }
    public HospitalImpl() throws RemoteException {
    }

    public String getName() throws RemoteException {
        return name;
    }

    public int getVacancies() throws RemoteException {
        return vacancies;
    }

    public Location getLocation() throws RemoteException {
        return location;
    }

    public Boolean isAvaliable() throws RemoteException {
        return avaliable;
    }

    public void occupyVacancy() throws RemoteException {
        if (avaliable) {
            this.vacancies--;
            this.avaliable = (vacancies > 0);
        }
    }
}


