package br.com.imd.pd.hospital.services;

import br.com.imd.pd.hospital.models.Hospital;
import br.com.imd.pd.hospital.models.Location;

public interface HospitalLocator {
    Hospital findNearestHospital(Location location) throws Exception;
}
