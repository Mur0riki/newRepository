package edu.hw6;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Task5 {
    public class HackerNews {
        private static final String HACKER_NEWS_TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
        private static final String HACKER_NEWS_ITEM_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
        private static final HttpClient httpClient = HttpClient.newHttpClient();

        public static long[] hackerNewsTopStories() {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HACKER_NEWS_TOP_STORIES_URL))
                    .build();

                HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {

                    // Преобразование JSON в массив типа long
                    String responseBody = response.body().toString();
                    responseBody = responseBody.substring(1, responseBody.length() - 1); // Удаляем начальную и конечную скобки
                    String[] results = responseBody.split(",");
                    long[] topStories = new long[results.length];
                    for (int i = 0; i < results.length; i++) {
                        topStories[i] = Long.parseLong(results[i].trim());
                    }
                    return topStories;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return new long[0]; // В случае ошибки ввода-вывода возвращаем пустой массив
        }

        public static String news(long id) {
            String newsTitle = "";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(HACKER_NEWS_ITEM_URL, id)))
                    .build();

                HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
// Достаем название новости с помощью регулярного выражения
                    Pattern pattern = Pattern.compile("\"title\"\\s*:\\s*\"(.*?)\"");
                    Matcher matcher = pattern.matcher(response.body().toString());

                    if (matcher.find()) {
                        newsTitle = matcher.group(1);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return newsTitle;
        }
    }
    public static void main(String[] args) {
        long[] topStories = HackerNews.hackerNewsTopStories();
        System.out.println(topStories.length > 0 ? "Top stories: " + java.util.Arrays.toString(topStories) : "Failed to retrieve top stories");

        String newsTitle = HackerNews.news(37570037);
        System.out.println(newsTitle.isEmpty() ? "Failed to retrieve news title" : "News title: " + newsTitle);
    }
}
