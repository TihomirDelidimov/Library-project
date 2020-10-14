package CommonStringValidation;

/**
 * This class is used to extract a common validation for strings
 */
public class CommonStringValidator {

    public static boolean isStringValid(String string) {
        return string != null && !string.trim().isEmpty();
    }
}
