package edu.project1;

class Session implements Dictionary {
    private final String answer;
    private char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session() {
        this.answer = getRandomword();
        this.userAnswer = new char[(this.answer.length())];
        this.maxAttempts = this.answer.length();
        this.attempts = 0;
    }

    int getAttempts() {
        return maxAttempts - attempts;
    }

    char[] getUserAnswer() {
        return userAnswer;
    }

    boolean stillPlayable() {
        return (attempts < maxAttempts);
    }

    boolean checkWin() {
        if (String.valueOf(userAnswer).equalsIgnoreCase(answer)) {
            System.out.println("Поздравляю, вы выиграли! Загаданное слово: " + answer);
            return true;
        }
        return false;
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

    @Override
    public String getRandomword() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }
}
