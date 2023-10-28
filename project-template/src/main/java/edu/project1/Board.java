package edu.project1;

public class Board {
    private final String answer;
    private char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Board(String answer) {
        this.answer = answer;
        this.maxAttempts = answer.length();
        this.userAnswer = new char[(this.answer.length())];
        this.attempts = 0;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAttempts() {
        return attempts;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
    public void setAttempts(int attempts){
        this.attempts = attempts;
    }
    public boolean stillPlayable(){
        return attempts < maxAttempts;
    }
}

