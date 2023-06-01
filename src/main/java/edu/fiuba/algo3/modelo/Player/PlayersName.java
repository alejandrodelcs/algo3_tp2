package edu.fiuba.algo3.modelo.Player;

public class PlayersName {

    public void validateName(String name){

        int nameSize = name.length();

        if(nameSize<6){
            throw new InvalidPlayersName();
        }
    }

}
