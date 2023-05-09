package br.com.imd.pd.hospital.models;
import java.rmi.RemoteException;

public interface Hospital {
    String getName() throws RemoteException;
    int getVacancies() throws RemoteException;
    Location getLocation() throws RemoteException;
    Boolean isAvaliable() throws RemoteException;
    void occupyVacancy() throws RemoteException;
}
