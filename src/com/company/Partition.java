package com.company;

import java.util.ArrayList;

public class Partition {
    private ArrayList<Radar> radars = new ArrayList<>();
    private String name;


    public Partition(String name){
        this.name = name;
    }
    public Partition(){
        name = " ";
    }
    public void addRadar(Radar radar){
        radars.add(radar);

    }
    public String getName(){
        return name;
    }
    public int getRadarSize(){
        return radars.size();
    }
    public Radar getRadars(int index){
        return radars.get(index);
    }
    public String toString(){
        return name + radars;
    }
}
