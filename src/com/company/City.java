package com.company;

import java.util.Locale;

public class City {
    private String name;
    private double latitude;
    private double longitude;
    public static final double RADIUS = 3963;


    public City(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public City(String name){
        this.name = name;
    }
    public City (){
        name = " ";
    }

    public String getName() {
        return name;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;


    }

    public double getLongitude() {
        return longitude;
    }

    public double distance(City city){

        double distance;
        double latitudeNotInstance = Math.toRadians(latitude);
        double latitudeInstance = Math.toRadians(city.getLatitude());
        double longitudeNotInstance = Math.toRadians(longitude);
        double longitudeInstance = Math.toRadians(city.getLongitude());
        distance = haversine(city.getLatitude(), city.getLongitude(), this.latitude, this.longitude);

        return distance;
    }
    public static double haversine(double latitude1, double longitude1,
                            double latitude2, double longitude2)
    {
        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLon = Math.toRadians(longitude2 - longitude1);

        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(latitude1) *
                        Math.cos(latitude2);;
        double c = 2 * Math.asin(Math.sqrt(a));
        return RADIUS * c;

    }
    public String toString(){
        return name + " " + latitude + " " + longitude;
    }
}
