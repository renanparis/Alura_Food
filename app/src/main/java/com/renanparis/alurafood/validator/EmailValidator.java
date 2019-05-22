package com.renanparis.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class EmailValidator implements Validator{

    private final TextInputLayout inputLayout;
    private final EditText emailField;
    private final StandardValidator standardValidator;

    public EmailValidator(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
        emailField = inputLayout.getEditText();
        standardValidator = new StandardValidator(this.inputLayout);
    }

    private boolean emailRequired(String email){

        if (email.matches(".+@.+\\..+")){
            return true;
        }else{
            inputLayout.setError("E-mail inválido");
            return false;
        }
    }

    public boolean isValid(){
        if (!standardValidator.isValid()) return false;
        String email = emailField.getText().toString();
        if (!emailRequired(email)) return false;

        return true;
    }
}
