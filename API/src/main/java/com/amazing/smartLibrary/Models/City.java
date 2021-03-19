package com.amazing.smartLibrary.Models;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String cityName, state, cityUID;
    private List<Library> librariesInCity;

    public City() {
    }

    public City(String cityName, String state) {
        this.cityName = cityName;
        this.state = state;
        librariesInCity = new ArrayList<>();
    }

    public boolean addLibrary(Library newLibrary) {
        try {
            librariesInCity.add(newLibrary);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removeLibrary(Library library) {
        try {
            librariesInCity.remove(library);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int totalLibrariesInCity() {
        return librariesInCity.size();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCityUID() {
        return cityUID;
    }

    public void setCityUID(String cityUID) {
        this.cityUID = cityUID;
    }

    public List<Library> getLibrariesInCity() {
        return librariesInCity;
    }

    public void setLibrariesInCity(List<Library> librariesInCity) {
        this.librariesInCity = librariesInCity;
    }
}
