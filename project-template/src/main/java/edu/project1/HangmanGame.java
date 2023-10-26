package edu.project1;

import java.util.Scanner;

public class HangmanGame {
    Player player;
    String word;

    HangmanGame(Player player, String guessedWord) {
        this.player = player;
        this.word = guessedWord;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите слово которое нужно будет угадывать.");
        Board board = new Board(in.nextLine());
        RandomPlayer player = new RandomPlayer();
        ConsoleHangman session = new ConsoleHangman(player,board);
        while (board.stillPlayable()){
            session.play();
        }
    }
}
