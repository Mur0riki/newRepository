package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
public class Task4 {
    public class OutputStreamCompositionExample {

        public static void main(String[] args) {
            // Путь к файлу
            Path filePath = Path.of("output.txt");

            // Создаем цепочку OutputStream'ов
            try (OutputStream fileOutputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);
                 CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
                 PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

                // Записываем текст в файл
                printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");

                // Печатаем контрольную сумму
                System.out.println("Checksum: " + checkedOutputStream.getChecksum().getValue());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
