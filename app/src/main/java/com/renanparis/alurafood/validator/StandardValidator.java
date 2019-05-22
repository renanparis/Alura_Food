package com.renanparis.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class StandardValidator implements Validator{

    public static final String REQUIRED_FIELD = "Campo Obrigatório";
    private TextInputLayout inputLayoutField;
    private EditText field;

    public StandardValidator(TextInputLayout inputLayoutField) {
        this.inputLayoutField = inputLayoutField;
        this.field = this.inputLayoutField.getEditText();
    }


    private boolean confirmRequiredField() {
        String text = field.getText().toString();
        if (text.isEmpty()) {
            inputLayoutField.setError(REQUIRED_FIELD);
            return false;
        }
        return true;

    }

    public boolean isValid() {
        if (!confirmRequiredField()) return false;
        removeError();
        return true;
    }

    private void removeError() {
        inputLayoutField.setError(null);
        inputLayoutField.setErrorEnabled(false);
    }
}
