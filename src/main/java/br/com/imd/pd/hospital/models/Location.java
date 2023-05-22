package br.com.imd.pd.hospital.models;

public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Location() {
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distance(Location other) {
        double lat1 = Math.toRadians(latitude);
        double lon1 = Math.toRadians(longitude);
        double lat2 = Math.toRadians(other.getLatitude());
        double lon2 = Math.toRadians(other.getLongitude());

        double dlat = lat2 - lat1;
        double dlong = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2),2) + Math.cos(dlat)*Math.pow(Math.sin(dlong / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a)) ;
        double distance = 6371000 * c;
        return distance;
    }
}
