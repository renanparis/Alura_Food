package com.renanparis.alurafood.validator;

import android.util.Log;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CpfValidator implements Validator {

    private static final String INVALID_CPF = "CPF inválido";
    private static final String ERROR_FORMAT_CPF = "erro formatação cpf";
    private static final String CPF_ELEVEN_DIGITS = "CPF precisa ter 11 dígitos";
    private final TextInputLayout inputLayout;
    private final EditText field;
    private final StandardValidator standardValidator;
    private final CPFFormatter cpfFormatter = new CPFFormatter();


    public CpfValidator(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
        this.field = this.inputLayout.getEditText();
        standardValidator = new StandardValidator(inputLayout);
    }


    private boolean confirmValidCPF(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            inputLayout.setError(INVALID_CPF);


            return false;
        }
        return true;
    }


    private boolean confirmRequiredElevenDigits(String cpf) {
        if (cpf.length() != 11) {
            inputLayout.setError(CPF_ELEVEN_DIGITS);
            return false;
        }
        return true;
    }

    public boolean isValid() {
        if (!standardValidator.isValid()) return false;
        String cpf = getCpf();
        String cpfUnformatted = cpf;

        try {
            cpfUnformatted = cpfFormatter.unformat(cpf);
        } catch (IllegalArgumentException e) {
            Log.e(ERROR_FORMAT_CPF, e.getMessage());
        }
        if (!confirmRequiredElevenDigits(cpfUnformatted)) return false;
        if (!confirmValidCPF(cpfUnformatted)) return false;
        addCpfFormat(cpfUnformatted);
        return true;
    }

    private void addCpfFormat(String cpf) {
        String cpfFormatted = cpfFormatter.format(cpf);
        field.setText(cpfFormatted);
    }

    private String getCpf() {
        return field.getText().toString();
    }
}
