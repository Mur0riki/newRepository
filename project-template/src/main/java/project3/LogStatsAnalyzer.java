package project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LogStatsAnalyzer {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public static void main(String[] args) {
            System.out.println("Usage: java -jar nginx-log-stats.jar --path <log-files> [--from <date>] [--format <output-format>]");

        String logPath = null;
        LocalDate fromDate = null;
        LocalDate toDate = null;
        String outputFormat = "markdown";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--path")) {
                logPath = args[i + 1];
                i++;
            } else if (args[i].equals("--from")) {
                fromDate = LocalDate.parse(args[i + 1], DATE_FORMAT);
                i++;
            } else if (args[i].equals("--to")) {
                toDate = LocalDate.parse(args[i + 1], DATE_FORMAT);
                i++;
            } else if (args[i].equals("--format")) {
                outputFormat = args[i + 1].toLowerCase();
                i++;
            }
        }

        LogStatsAnalyzer analyzer = new LogStatsAnalyzer();
        try {
            analyzer.analyzeLogs(logPath, fromDate, toDate, outputFormat);
        } catch (IOException e) {
            System.out.println("Error reading log files: " + e.getMessage());
        }
    }

    private void analyzeLogs(String logPath, LocalDate fromDate, LocalDate toDate, String outputFormat) throws IOException {
        Map<String, Integer> resourceCountMap = new HashMap<>();
        Map<Integer, Integer> responseCodeCountMap = new HashMap<>();
        int requestCount = 0;
        int totalResponseSize = 0;

        // Process log files
        if (logPath.startsWith("http")) {
            URL url = new URL(logPath);
            try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
                processLogFile(reader, fromDate, toDate, resourceCountMap, responseCodeCountMap, requestCount, totalResponseSize);
            }
        } else {
            Path path = Paths.get(logPath);
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                processLogFile(reader, fromDate, toDate, resourceCountMap, responseCodeCountMap, requestCount, totalResponseSize);
            }
        }

        int averageResponseSize = (requestCount > 0) ? totalResponseSize / requestCount : 0;

        if (outputFormat.equals("markdown")) {
            printMarkdownStats(logPath, fromDate, toDate, requestCount, averageResponseSize, resourceCountMap, responseCodeCountMap);
        } else {
            printAdocStats(logPath, fromDate, toDate, requestCount, averageResponseSize, resourceCountMap, responseCodeCountMap);
        }
    }

    private void processLogFile(BufferedReader reader, LocalDate fromDate, LocalDate toDate, Map<String, Integer> resourceCountMap, Map<Integer, Integer> responseCodeCountMap, int requestCount, int totalResponseSize) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length >= 9) {
                String dateStr = parts[0];
                String timeStr = parts[1];
                String resource = parts[6];
                int responseCode = Integer.parseInt(parts[8]);
                int responseSize = Integer.parseInt(parts[9]);

                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("[dd/MMM/yyyy:HH:mm:ssX][dd/MMM/yyyy:HH:mm:ss X][dd/MM/yyyy:HH:mm:ss Z][dd/MMM/yyyy:HH:mm:ss Z]"));

                if ((fromDate == null || !date.isBefore(fromDate)) && (toDate == null || !date.isAfter(toDate))) {
                    resourceCountMap.put(resource, resourceCountMap.getOrDefault(resource, 0) + 1);
                    responseCodeCountMap.put(responseCode, responseCodeCountMap.getOrDefault(responseCode, 0) + 1);
                    requestCount++;
                    totalResponseSize += responseSize;
                }
            }
        }
    }

    private void printMarkdownStats(String logPath, LocalDate fromDate, LocalDate toDate, int requestCount, int averageResponseSize, Map<String, Integer> resourceCountMap, Map<Integer, Integer> responseCodeCountMap) {
        System.out.println("#### Общая информация");
        System.out.println();
        System.out.println("| Метрика                | Значение     |");
        System.out.println("|:-----------------------|-------------:|");
        System.out.println("| Файл(-ы)               | " + logPath + "  |");
        System.out.println("| Начальная дата         | " + (fromDate != null ? fromDate.format(DATE_FORMAT) : "-") + " |");
        System.out.println("| Конечная дата          | " + (toDate != null ? toDate.format(DATE_FORMAT) : "-")  + "  |");
        System.out.println("| Количество запросов    | " + requestCount + "      |");
        System.out.println("| Средний размер ответа  | " + averageResponseSize + "b    |");
        System.out.println();

        System.out.println("#### Наиболее часто запрашиваемые ресурсы");
        System.out.println();
        System.out.println("| Ресурс                  | Частота      |");
        System.out.println("|:-----------------------|-------------:|");
        for (Map.Entry<String, Integer> entry : resourceCountMap.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + " |");
        }
        System.out.println();

        System.out.println("#### Наиболее часто встречающиеся коды ответа");
        System.out.println();
        System.out.println("| Код ответа             | Частота      |");
        System.out.println("|:-----------------------|-------------:|");
        for (Map.Entry<Integer, Integer> entry : responseCodeCountMap.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private void printAdocStats(String logPath, LocalDate fromDate, LocalDate toDate, int requestCount, int averageResponseSize, Map<String, Integer> resourceCountMap, Map<Integer, Integer> responseCodeCountMap) {
        System.out.println("= Общая информация");
        System.out.println();
        System.out.println("| Метрика                | Значение     |");
        System.out.println("|------------------------|-------------:|");
        System.out.println("| Файл(-ы)               | " + logPath + "  |");
        System.out.println("| Начальная дата         | " + (fromDate != null ? fromDate.format(DATE_FORMAT) : "-") + " |");
        System.out.println("| Конечная дата          | " + (toDate != null ? toDate.format(DATE_FORMAT) : "-")  + "  |");
        System.out.println("| Количество запросов    | " + requestCount + "      |");
        System.out.println("| Средний размер ответа  | " + averageResponseSize + "b    |");
        System.out.println();

        System.out.println("= Наиболее часто запрашиваемые ресурсы");
        System.out.println();
        System.out.println("| Ресурс                  | Частота      |");
        System.out.println("|------------------------|-------------:|");
        for (Map.Entry<String, Integer> entry : resourceCountMap.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + " |");
        }
        System.out.println();

        System.out.println("= Наиболее часто встречающиеся коды ответа");
        System.out.println();
        System.out.println("| Код ответа             | Частота      |");
        System.out.println("|------------------------|-------------:|");
        for (Map.Entry<Integer, Integer> entry : responseCodeCountMap.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + " |");
        }
        System.out.println();
    }
}
