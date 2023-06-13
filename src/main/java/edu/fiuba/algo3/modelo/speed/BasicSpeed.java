package edu.fiuba.algo3.modelo.speed;

import java.awt.*;
import java.util.ArrayList;

public class BasicSpeed implements Speed{

    private int speed;
    private int turns;
    public BasicSpeed(int speed){
        this.speed = speed;
    }
    public Point moveWithSpeed(int plotListIndex, ArrayList<Point> coordinatesList){

        int currentPlotListIndex = speed + plotListIndex;

        if(  (currentPlotListIndex < coordinatesList.size()-1)) {
            return coordinatesList.get(currentPlotListIndex);
        }
        return coordinatesList.get(coordinatesList.size()-1);
    };
}