package capstone.project.Demo_Project.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtility {
    public static String validateSpecialCharacters(String request) {
        Pattern p = Pattern.compile(Constants.REGEX_SPECIAL_CHARACTERS);
        Matcher m = p.matcher(request);
        return m.replaceAll(Constants.REGEX_REPLACED_SPECIAL_CHARACTERS);
    }
}
