package com.example.demo13;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || value.toString().isEmpty()) {
            return; // Consider empty fields as valid.
        }

        if (!value.toString().matches(EMAIL_PATTERN)) {
            throw new ValidatorException(new FacesMessage("Invalid email format.")); // TODO:i18n
        }
    }
}
