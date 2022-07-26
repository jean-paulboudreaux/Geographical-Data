package com.company;

import java.util.ArrayList;

public class State {
    private String name;
    private String abbreviation;
    private ArrayList<City> cities = new ArrayList<>();
    private int population;
    private int surfaceArea;
    private int index = 0;

    public State(String name, String abbreviation){
        this.name = name;
        this.abbreviation = abbreviation;
        index++;

    }
    public State(String name, String abbreviation, int population, int surfaceArea){
        this.name = name;
        this.abbreviation = abbreviation;
        this.population = population;
        this.surfaceArea = surfaceArea;
        index++;
    }
    public State(String name){
        this.name = name;
        index++;
    }
    public State(String name, City city){
        this.name = name;
    }
   // public State(String abbreviations){
       // abbreviation = abbreviations;
   // }
    public State(){
        name = " ";
    }
    public boolean equals(Object o) {
        if(o instanceof  State) {
            State otherState = (State) o;
            return this.name.equals(otherState.name);
        }
        return false;
    }

    public void setPopulation(int population) {

        this.population = population;
    }

    public int getIndex() {
        return index;
    }

    public int getPopulation() {
        return population;
    }

    public void addCity(City city){
       cities.add(city);
    }

    public int getSurfaceArea() {
        return surfaceArea;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public String getName(){
        return name;
    }
    public String getAbbreviation(){
        return abbreviation;
    }

    public void setSurfaceArea(int surfaceArea) {
        this.surfaceArea = surfaceArea;
    }
    public String toString(){
        return name + " " + abbreviation;
    }
}
