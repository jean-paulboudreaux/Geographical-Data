package com.company;
public class Radar {
    private String ICOA;
    private City city;
    private State state;

    public Radar(String ICOA, City city, State state){
        this.ICOA = ICOA;
        this.city = city;
        this.state = state;

    }
    public Radar(){
        ICOA = " ";
    }
    public Radar(String ICOA){
        this.ICOA = ICOA;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public String getICOA() {
        return ICOA;
    }

    public String toString(){
        return ICOA + " " + city.getName() + " " + state.getName() + " " +  state.getAbbreviation() + " " + state.getPopulation() + " " +  state.getSurfaceArea() + " " + city.getLatitude() + " "  + city.getLongitude();
    }
}


