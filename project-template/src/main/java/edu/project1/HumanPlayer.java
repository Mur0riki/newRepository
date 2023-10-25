package edu.project1;

public class HumanPlayer implements Player {
    String name;
    public HumanPlayer(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
