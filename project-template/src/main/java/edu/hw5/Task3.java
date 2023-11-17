package edu.hw5;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;


public class Task3 {
    public class DateParser {
        public static Optional<LocalDate> parseDate(String string) {
            // Проверяем формат "yyyy-MM-dd"
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(string, formatter);
                return Optional.of(date);
            } catch (DateTimeParseException ignored) {
            }

            // Проверяем формат "M/d/yyyy"
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                LocalDate date = LocalDate.parse(string, formatter);
                return Optional.of(date);
            } catch (DateTimeParseException ignored) {
            }

            // Проверяем формат "M/d/yy"
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
                LocalDate date = LocalDate.parse(string, formatter);
                return Optional.of(date);
            } catch (DateTimeParseException ignored) {
            }

            // Проверяем относительные значения
            if (string.equalsIgnoreCase("today") || string.equalsIgnoreCase("сегодня")) {
                LocalDate date = LocalDate.now();
                return Optional.of(date);
            } else if (string.equalsIgnoreCase("yesterday") || string.equalsIgnoreCase("вчера")) {
                LocalDate date = LocalDate.now().minusDays(1);
                return Optional.of(date);
            } else if (string.equalsIgnoreCase("tomorrow") || string.equalsIgnoreCase("завтра")) {
                LocalDate date = LocalDate.now().plusDays(1);
                return Optional.of(date);
            } else if (string.endsWith(" day ago") || string.endsWith(" день назад")) {
                try {
                    int days = Integer.parseInt(string.split(" ")[0]);
                    LocalDate date = LocalDate.now().minusDays(days);
                    return Optional.of(date);
                } catch (NumberFormatException ignored) {
                }
            } else if (string.endsWith(" days ago") || string.endsWith(" дней назад")) {
                try {
                    int days = Integer.parseInt(string.split(" ")[0]);
                    LocalDate date = LocalDate.now().minusDays(days);
                    return Optional.of(date);
                } catch (NumberFormatException ignored) {
                }
            }

            // Если ни один формат не подходит, возвращаем Optional.empty()
            return Optional.empty();
        }
    }
}
