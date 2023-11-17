package edu.hw6;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class Task2 {
        public class FileCloner {
        public static void cloneFile(Path path) {
            try {
                // Получаем имя файла и расширение
                String fileName = path.getFileName().toString();
                String extension = "";
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex >= 0) {
                    extension = fileName.substring(dotIndex);
                    fileName = fileName.substring(0, dotIndex);
                }
                // Генерируем новое имя файла
                String cloneFileName = fileName + " (копия)" + extension;
                // Создаем путь к новому файлу
                Path clonePath = path.resolveSibling(cloneFileName);

                // Копируем файл с новым именем
                Files.copy(path, clonePath);

                System.out.println("Файл успешно скопирован с новым именем: " + clonePath);
            } catch (IOException e) {
                System.out.println("Ошибка при копировании файла: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            // Пример вызова функции
            Path filePath = Path.of("C:\\путь\\к\\файлу.txt");
            cloneFile(filePath);
        }
    }
}
