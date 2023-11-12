package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Task1 {
    public String averageTime(String[] array) {
        long[] timeinminutes = new long[array.length];
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        for (int i = 0; i < array.length; i++) {
            String[] splittedline = array[i].split(" - ");
            try {
                Date date1 = formater.parse(splittedline[0]);
                Date date2 = formater.parse(splittedline[1]);
                Instant instant1 = date1.toInstant();
                Instant instant2 = date2.toInstant();
                Duration duration = Duration.between(instant1, instant2);
                long diffInMinutes = duration.toMinutes();
                timeinminutes[i] = diffInMinutes;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        int result = 0;
        for (long number : timeinminutes) {
            result += number;
        }
        return result / 60 + "ч " + result % 60 + "м";
    }
}
