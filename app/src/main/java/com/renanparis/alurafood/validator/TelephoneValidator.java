package com.renanparis.alurafood.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.renanparis.alurafood.formatter.TelephoneDDDFormatter;

public class TelephoneValidator implements Validator {

    private static final String TEN_OUR_ELEVEN_DIGITS = "Telefone precisa ter 10 ou 11 dígitos";
    private final TextInputLayout inputLayout;
    private final EditText telephoneField;
    private final TelephoneDDDFormatter formatter = new TelephoneDDDFormatter();
    private final StandardValidator standardValidator;


    public TelephoneValidator(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
        telephoneField = inputLayout.getEditText();
        this.standardValidator = new StandardValidator(inputLayout);
    }

    private boolean tenOurElevenDigits(String telephone) {

        int telephoneFull = telephone.length();

        if (telephoneFull < 10 || telephoneFull > 11) {
            inputLayout.setError(TEN_OUR_ELEVEN_DIGITS);
            return false;
        }
        return true;
    }

    public boolean isValid() {

        if (!standardValidator.isValid()) return false;
        String telephone = telephoneField.getText().toString();
        String unformattedPhone = formatter.removePhoneFormat(telephone);
        if (!tenOurElevenDigits(unformattedPhone)) return false;
        telephoneFieldFormatter(unformattedPhone);
        return true;

    }

    private void telephoneFieldFormatter(String telephone) {
        String telephoneFormatted = formatter.telephoneFormatter(telephone);
        telephoneField.setText(telephoneFormatted);

    }


}
