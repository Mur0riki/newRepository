package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static List<LocalDate> findFridayTheThirteenths(int year) {
        List<LocalDate> fridayTheThirteenths = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 13); // Начинаем с 13-го января указанного года
        for (int month = 1; month <= 12; month++) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                fridayTheThirteenths.add(date);
            }
            date = date.plusMonths(1); // Переходим к следующему месяцу
        }
        return fridayTheThirteenths;
    }
    public LocalDate findTheNearestNextFridayTheThirteenths(LocalDate date){
        List<LocalDate> fridayTheThirteenths = findFridayTheThirteenths(date.getYear());
        LocalDate date1 = null;
        for(LocalDate date2:fridayTheThirteenths){
            if(date2.isBefore(date) && !date1.equals(null)){
                date1 = date2;
            }
        }
        if (date1.equals(null)) {
            fridayTheThirteenths = findFridayTheThirteenths(date.getYear() + 1);
            date1 = fridayTheThirteenths.get(0);
        }
        return date1;
    }

    public static void main(String[] args) {
        List fridayTheThirteenths = new ArrayList<>();
        fridayTheThirteenths = findFridayTheThirteenths(1925);
        System.out.println(fridayTheThirteenths);
    }
}
