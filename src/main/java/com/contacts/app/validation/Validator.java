package com.contacts.app.validation;

import com.contacts.app.model.Contact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class Validator {
    public void validate(Contact contact, Errors errors) {
        if (!contact.getPhone().matches("^\\+?\\d{5,15}$")) {
            errors.rejectValue("phone", "invalide.phone", "Invalid phone number");
        }
        if (!contact.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.rejectValue("email", "invalid.email", "Invalid email");
        }
    }
}
