package test;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String pattern = "(.){1,2000}"; //최대 2000글자
        String val = "123456789\r"; //대상문자열
        boolean regex = Pattern.matches(pattern, val);
        System.out.println(regex);

    }
}
