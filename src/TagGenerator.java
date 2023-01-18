import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

public class TagGenerator {
    public static String generateTag(String name, String lastname, String city, Date date){
        StringBuilder result = new StringBuilder();
        String[] nameletters = lastname.toUpperCase().split("");
        int i = 0;
        int numbersOfConsonants = 0;
        String el = "";
        for(String letter: nameletters){
            switch(letter){
                case "A", "E", "I", "O", "U" -> {
                    if (el.equals("")) {
                        el = letter;
                    }
                }
                default -> {
                    if (i!=3){
                        result.append(letter);
                        i++;
                        numbersOfConsonants++;
                    }
                }
            }
        }
        if(numbersOfConsonants <= 2){
            result.append(el);
        }
        if(numbersOfConsonants == 1){
            result.append("X");
        }
        System.out.println("First part created: " + result.toString());

        nameletters = name.toUpperCase().split("");
        i = 0;
        numbersOfConsonants = 0;
        el = "";
        for(String letter: nameletters){
            switch(letter){
                case "A", "E", "I", "O", "U" -> {
                    if (el.equals("")) {
                        el = letter;
                    }
                }
                default -> {
                    if (i!=3){
                        result.append(letter);
                        i++;
                        numbersOfConsonants++;
                    }
                }
            }
        }
        if(numbersOfConsonants <= 2){
            result.append(el);
        }
        if(numbersOfConsonants == 1){
            result.append("X");
        }
        System.out.println("Second part created: " + result.toString());

        nameletters = city.toUpperCase().split("");
        i = 0;
        numbersOfConsonants = 0;
        el = "";
        for(String letter: nameletters){
            switch(letter){
                case "A", "E", "I", "O", "U" -> {
                    if (el.equals("")) {
                        el = letter;
                    }
                }
                default -> {
                    if (i!=2){
                        result.append(letter);
                        i++;
                        numbersOfConsonants++;
                    }
                }
            }
        }
        if(numbersOfConsonants <= 1){
            result.append(el);
        }
        System.out.println("Last part created: " + result.toString());

        System.out.println("Getting calendar...");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        result.append(cal.get(Calendar.DAY_OF_MONTH)%10);
        System.out.println("First digit created: " +cal.get(Calendar.DAY_OF_MONTH)%10);

        result.append(cal.get(Calendar.MONTH)+1%10);
        System.out.println("Second digit created: " +cal.get(Calendar.MONTH)+1%10);

        result.append(cal.get(Calendar.YEAR)%10);
        System.out.println("Third digit created: " +cal.get(Calendar.YEAR)%10);

        return result.toString();
    }

    public static int randInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
