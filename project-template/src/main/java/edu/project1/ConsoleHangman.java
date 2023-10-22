package edu.project1;

import java.util.Scanner;

public class ConsoleHangman {
    public void play() {
        Scanner in = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру \"Виселица\".Если желаете закончить досрочно напишите \"exit\"");
        Session game = new Session();
        while (game.stillPlayable()) {
            System.out.println("Введите букву");
            String guess = in.nextLine();
            if (guess.equals("exit")) {
                break;
            }
            if (guess.length() == 1) {
                char letter = guess.charAt(0);
                if (game.guessResult(letter)) {
                    System.out.println("Поздравляю, вы угадали букву!");
                } else {
                    System.out.println("Упс, неправильная буква. Осталось попыток: " + game.getAttempts());
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите одну букву или слово целиком.");
            }
            System.out.println("Угаданные буквы: " + String.valueOf(game.getUserAnswer()));
            if (game.checkWin()) {
                break;
            }
        }
        if (!game.stillPlayable()) {
            System.out.println("к сожалению вы проиграли.");
        }
    }

    public static void main(String[] args) {
        ConsoleHangman game = new ConsoleHangman();
        game.play();
    }
}
