package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final String[] KEYWORDS = {
        "личности",
        "оскорбления",
        "глупый",
        "интеллект"};
    private static final String[] RESPONSES = {"Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"};
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен. Ожидание подключения...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Подключение установлено. Ожидание запроса...");

                try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ) {
                    while (true) {
                        String keyword = reader.readLine();

                        // Проверяем введенное ключевое слово
                        boolean found = false;
                        for (int i = 0; i < KEYWORDS.length; i++) {
                            if (KEYWORDS[i].equalsIgnoreCase(keyword)) {
                                writer.write(RESPONSES[i]);
                                writer.newLine();
                                writer.flush();
                                found = true;
                                break;
                            }
                        }
                        // Проверяем команду для завершения соединения
                        if (keyword.equalsIgnoreCase("выход")) {
                            break;
                        }
                        // Если ключевое слово не найдено, отправляем ответ по умолчанию
                        if (!found && keyword.equalsIgnoreCase("выход")) {
                            writer.write("Извините, не могу дать адекватный ответ на ваше сообщение");
                            writer.newLine();
                            writer.flush();
                        }

                    }

                    System.out.println("Соединение разорвано.");
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
