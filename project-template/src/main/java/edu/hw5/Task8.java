package edu.hw5;

public class Task8 {
    public static void main(String[] args) {
        String regex1 = "[01](?:[01]{2})*";
        String regex2 = "(0(?:[01]{2})*)|(1(?:[01]{2})*[01])";
        String regex3 = "(?:[01]*0){3}[01]*";
        String regex4 = "(?!1{2,3}$)[01]*";
        String regex5 = "^([01]|(?:[01]{2})*1)*$";
        String regex6 = "(?=.*0.*0)(?!.*1.*1)^[01]*$";
        String regex7 = "(?!.*11)^[01]*$";
    }
}
