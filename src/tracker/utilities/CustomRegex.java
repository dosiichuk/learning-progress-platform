package tracker.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRegex {

    public static boolean verifyName(String name) {
        return Pattern.matches("^(?!.*[-']{2})[a-zA-Z ][-a-zA-Z ']*[a-zA-Z ]$", name);
    }

    public static boolean verifyEmail(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9-.]+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
