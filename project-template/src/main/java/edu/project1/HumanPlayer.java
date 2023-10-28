package edu.project1;

public class HumanPlayer implements Player {
    String name;
    public HumanPlayer(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public int nextMove(Board board) {
        return 0;
    }

    @Override
    public int checkSymbol(String guess, Board board) {
        return 0;
    }

    @Override
    public boolean guessResult(char letter, Board board) {
        return false;
    }
}
