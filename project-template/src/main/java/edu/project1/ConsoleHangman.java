package edu.project1;

public class ConsoleHangman {
    Player player;
    String word;
    Board board;

    ConsoleHangman(RandomPlayer player, Board board) {
        this.player = player;
        this.board = board;
        this.word = board.getAnswer();
    }

    void play() {
        if (this.word.length() < 1) {
            return;
        }
        System.out.println("Добро пожаловать в игру \"Виселица\".Если желаете закончить досрочно напишите \"exit\"");
        while (board.stillPlayable()) {
            int i = player.nextMove(board);
            if (i == -1) {
                break;
            } else if(i == -2){
                System.out.println("Вы проиграли.");
                break;
            }
        }
    }
}
