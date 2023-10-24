package edu.project1;

import java.util.Scanner;

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

    public int getSymbol() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите букву");
        String guess = in.nextLine();
        return checkSymbol(guess);
    }

    public int checkSymbol(String guess) {
        if (guess.equals("exit")) {
            return -1;
        }
        if (guess.length() == 1) {
            char letter = guess.charAt(0);
            if (this.guessResult(letter)) {
                System.out.println("Поздравляю, вы угадали букву!");
                if (String.valueOf(userAnswer).equalsIgnoreCase(answer)) {
                    System.out.println("Поздравляю, вы выиграли! Загаданное слово: " + answer);
                    return 2;
                } else {
                    System.out.println("Угаданные буквы: " + String.valueOf(userAnswer));
                    return 1;
                }
            } else {
                if(maxAttempts - attempts == 0){
                    return -2;
                }
                System.out.println("Упс, неправильная буква. Осталось попыток: " + (maxAttempts - attempts));
                return 0;
            }
        } else {
            System.out.println("Неверный ввод. Пожалуйста, введите одну букву .");
            return 0;
        }
    }

    boolean guessResult(char letter) {
        boolean found = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter) {
                userAnswer[i] = letter;
                found = true;
            }
        }
        if (!found) {
            attempts++;
        }
        return found;
    }

    boolean stillPlayable() {
        return (attempts < maxAttempts);
    }
}

