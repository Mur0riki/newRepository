package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                System.out.print("Введите ключевое слово (выход для завершения): ");
                String keyword = consoleReader.readLine();

                // Отправляем ключевое слово на сервер
                writer.write(keyword);
                writer.newLine();
                writer.flush();

                // Проверяем ответ от сервера
                String response = reader.readLine();
                // Проверяем команду для завершения соединения
                if (keyword.equalsIgnoreCase("выход")) {
                    break;
                }
                System.out.println("Ответ сервера: " + response);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
