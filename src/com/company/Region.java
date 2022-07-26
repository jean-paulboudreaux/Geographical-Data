package com.company;

import java.util.ArrayList;

public class Region extends Partition{
    private ArrayList<State> states = new ArrayList<>();
    private int index = 0;

    public Region(String name){
        super(name);
    }
    public Region(){
        super("");
    }
    public void addStates(State state){
        states.add(state);
        index++;
    }
    public int getIndex(){
        return index;
    }

    public int getState(int index) {
        return states.get(index).getPopulation();
    }

}



