import java.util.Random;

public class TagGenerator {
    public static String generateTag(String name, String lastname, String city, String date){
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
        if(numbersOfConsonants == 2){
            result.append(el);
        }

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
        if(numbersOfConsonants == 2){
            result.append(el);
        }

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
        if(numbersOfConsonants == 1){
            result.append(el);
        }

        nameletters = date.split("");
        result.append(nameletters[1]);
        result.append(nameletters[4]);
        result.append(nameletters[9]);

        return result.toString();
    }

    public static int randInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
