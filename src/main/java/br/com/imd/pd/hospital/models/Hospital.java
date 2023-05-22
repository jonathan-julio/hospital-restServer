package br.com.imd.pd.hospital.models;

public class Hospital {
    private String name;
    private int vacancies;
    private Location location;
    private boolean avaliable;

    public Hospital(String name, int vacancies, Location location) {
        this.name = name;
        this.vacancies = vacancies;
        this.location = location;
        this.avaliable = (vacancies > 0);
    }

    public Hospital() {
    }

    public String getName() {
        return name;
    }

    public int getVacancies() {
        return vacancies;
    }

    public Location getLocation() {
        return location;
    }

    public Boolean isAvaliable() {
        return avaliable;
    }

    public void occupyVacancy() {
        if (avaliable) {
            this.vacancies--;
            this.avaliable = (vacancies > 0);
        }
    }
}


