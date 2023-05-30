package edu.fiuba.algo3.modelo;

public class PlayersName {

    public void validateName(String name){

        int nameSize = name.length();

        if(nameSize<6){
            throw new InvalidPlayersName();
        }
    }

}
