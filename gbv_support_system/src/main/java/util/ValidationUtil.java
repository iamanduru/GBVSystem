package util;

public class ValidationUtil {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    //Validating phone number
    public static boolean isValidPhoneNumber(String phone) {
        // Kenyan phone number (07xx xxx xxx or +2547xx xxx xxx)
        return phone != null && phone.matches("^(\\+254|0)7\\d{8}$");
    }

    //Validating the length
    public static boolean isValidLength(String value, int min, int max) {
        return value != null && value.length() >= min && value.length() <= max;
    }

    //Validating a positive number
    public static boolean isPositiveInteger(int number) {
        return number > 0;
    }

    //validating an Email
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    }

    //Validating a location
    public static boolean isValidLocation(String location) {
        return location != null && location.length() >= 3;
    }

    //Validating the contact info and saving
    public static boolean isValidContactInfo(String contact) {
        return isValidPhoneNumber(contact) || isValidEmail(contact);
    }

    public static boolean isValidName(String name) {
        return !isNullOrEmpty(name) && name.length() >= 2;
    }
    
    public static boolean isValidDescription(String desc) {
        return desc == null || desc.length() <= 500; 
    }
    
}

