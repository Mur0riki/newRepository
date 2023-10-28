package edu.project1;

import java.util.Scanner;

public class RandomPlayer implements Player {
    @Override
    public String getName() {
        return null;
    }


    public int nextMove(Board board) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите букву");
        String guess = in.nextLine();
        return checkSymbol(guess, board);
    }

    public int checkSymbol(String guess, Board board) {
        if (guess.equals("exit")) {
            return -1;
        }
        if (guess.length() == 1) {
            char letter = guess.charAt(0);
            if (this.guessResult(letter, board)) {
                System.out.println("Поздравляю, вы угадали букву!");
                if (String.valueOf(board.getUserAnswer()).equalsIgnoreCase(board.getAnswer())) {
                    System.out.println("Поздравляю, вы выиграли! Загаданное слово: " + board.getAnswer());
                    return 2;
                } else {
                    System.out.println("Угаданные буквы: " + String.valueOf(board.getUserAnswer()));
                    return 1;
                }
            } else {
                if (board.getMaxAttempts() - board.getAttempts() == 0) {
                    return -2;
                }
                System.out.println(
                    "Упс, неправильная буква. Осталось попыток: " + (board.getMaxAttempts() - board.getAttempts()));
                return 0;
            }
        } else {
            System.out.println("Неверный ввод. Пожалуйста, введите одну букву .");
            return 0;
        }
    }

    public boolean guessResult(char letter, Board board) {
        boolean found = false;
        for (int i = 0; i < board.getAnswer().length(); i++) {
            if ((board.getAnswer()).charAt(i) == letter) {
                board.getUserAnswer()[i] = letter;
                found = true;
            }
        }
        if (!found) {
            int attempts = board.getAttempts() + 1;
            board.setAttempts(attempts);
        }
        return found;
    }
}
