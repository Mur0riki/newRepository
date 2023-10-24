package edu.project1;

public class ConsoleHangman {
    Player player;
    String word;

    ConsoleHangman(Player player, String guessedWord) {
        this.player = player;
        this.word = guessedWord;
    }

    void play() {
        if (this.word.length() < 1) {
            return;
        }
        Board board = new Board(this.word);
        System.out.println("Добро пожаловать в игру \"Виселица\".Если желаете закончить досрочно напишите \"exit\"");
        while (board.stillPlayable()) {
            int i = board.getSymbol();
            if (i == -1) {
                break;
            } else if(i == -2){
                System.out.println("Вы проиграли.");
                break;
            }
        }
    }
}
