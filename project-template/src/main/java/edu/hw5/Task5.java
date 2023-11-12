package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    public static boolean validateLicensePlate(String licensePlate) {
        String regex = "^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(licensePlate);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String licensePlate1 = "А123ВЕ777";
        String licensePlate2 = "О777ОО177";
        String licensePlate3 = "123АВЕ777";
        String licensePlate4 = "А123ВГ77";
        String licensePlate5 = "А123ВЕ7777";

        System.out.println(licensePlate1 + " is valid? " + validateLicensePlate(licensePlate1));
        System.out.println(licensePlate2 + " is valid? " + validateLicensePlate(licensePlate2));
        System.out.println(licensePlate3 + " is valid? " + validateLicensePlate(licensePlate3));
        System.out.println(licensePlate4 + " is valid? " + validateLicensePlate(licensePlate4));
        System.out.println(licensePlate5 + " is valid? " + validateLicensePlate(licensePlate5));
    }
}
