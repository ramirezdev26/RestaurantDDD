package org.example.domain.client.values;

import org.example.generic.ValueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements ValueObject<String> {

    private String email;
    private static final String EMAIL_REGEX =
            "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Email(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else throw new IllegalArgumentException("The email should be a valid one");
    }
    @Override
    public String value() {
        return email;
    }
}
