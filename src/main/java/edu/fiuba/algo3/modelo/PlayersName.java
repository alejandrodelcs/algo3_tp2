package edu.fiuba.algo3.modelo;

public class PlayersName {
    private String playersName;

    public void validateName(String name){

        int nameSize = name.length();

        if(nameSize<6){
            throw new InvalidPlayersName();
        }
    }

}
