package edu.project1;

public interface Player {
    public String getName();
    public int nextMove(Board board);
    public int checkSymbol(String guess, Board board);
    public boolean guessResult(char letter, Board board);
}
