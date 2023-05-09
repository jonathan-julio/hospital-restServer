package br.com.imd.pd.hospital.models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AmbulanceService extends Remote {
    Hospital findNearestHospital(Location location) throws RemoteException;
}
