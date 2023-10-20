package edu.project1;

import java.util.Scanner;

public class HangmanGame {

    private static final String[] WORDS = {"apple", "banana", "cherry", "date", "elderberry"};
    private static final int MAX_TRIES = 6;

    public static void main(String[] args) {

        String secretWord = getRandomWord();
        char[] guessedLetters = new char[secretWord.length()];
        int tries = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру \"Виселица\"!Если вы желаете сдаться преждевременно закончив игру, напишите \"exit\"");

        while (tries < MAX_TRIES) {
            System.out.print("Угадайте букву или слово: ");
            String guess = scanner.nextLine();
            if(guess.equals("exit")){
                break;
            }
            if (guess.length() == 1) {
                char letter = guess.charAt(0);
                boolean found = false;

                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == letter) {
                        guessedLetters[i] = letter;
                        found = true;
                    }
                }

                if (found) {
                    System.out.println("Поздравляю, вы угадали букву!");
                } else {
                    tries++;
                    System.out.println("Упс, неправильная буква. Осталось попыток: " + (MAX_TRIES - tries));
                }

            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите одну букву.");
            }

            System.out.println("Угаданные буквы: " + String.valueOf(guessedLetters));

            if (String.valueOf(guessedLetters).equalsIgnoreCase(secretWord)) {
                System.out.println("Поздравляю, вы выиграли! Загаданное слово: " + secretWord);
                break;
            }
        }

        if (tries == MAX_TRIES) {
            System.out.println("Игра окончена! Вы проиграли. Загаданное слово: " + secretWord);
        }

        scanner.close();
    }

    private static String getRandomWord() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }
}
