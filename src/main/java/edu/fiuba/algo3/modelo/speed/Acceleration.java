package edu.fiuba.algo3.modelo.speed;

import java.awt.*;
import java.util.ArrayList;

public class Acceleration implements Speed{

    private int speed;
    private int currentTurn;
    public Acceleration(int givenSpeed){
        this.speed = givenSpeed;
        this.currentTurn = 0;
    }

    private void updateSpeed(){
        currentTurn++;
        //TODO: refactor brush up this logic
        if(currentTurn > 10){
            speed = 3;
        }
        if( (currentTurn <= 10) && (currentTurn > 5)){
            speed = 2;
        }
    }

    public Point moveWithSpeed(int plotListIndex, ArrayList<Point> coordinatesList){

        if(!(currentTurn>11)){updateSpeed();}

        //TODO: refactor extract method in a new movement model/class
        int currentPlotListIndex = speed + plotListIndex;

        if(  (currentPlotListIndex < coordinatesList.size()-1)) {
            return coordinatesList.get(currentPlotListIndex);
        }
        return coordinatesList.get(coordinatesList.size()-1);
    }
}
