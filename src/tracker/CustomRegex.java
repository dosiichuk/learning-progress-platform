package tracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRegex {
    public static boolean verifyThePresenceOfForbiddenCharacters(String name) {
        Pattern pattern = Pattern.compile("[^a-zA-Z\\s'-]");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean verifyName(String name) {
        return Pattern.matches("^(?!.*[-']{2})[a-zA-Z ][-a-zA-Z ']*[a-zA-Z ]$", name);
    }

    public static boolean verifyTheCorrectnessOfHyphenUse(String name) {
        if (!name.matches("-")) return true;
        Pattern pattern = Pattern.compile("[a-z]-[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean verifyTheCorrectnessOfApostropheUse(String name) {
        if (!name.matches("'")) return true;
        Pattern pattern = Pattern.compile("[a-z]'[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean verifyTheCorrectnessOfSpaceUse(String name) {
        if (!name.matches(" ")) return true;
        Pattern pattern = Pattern.compile("[a-z]\\s[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean verifyEmail(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9-.]+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
