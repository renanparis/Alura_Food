package com.renanparis.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.renanparis.alurafood.R;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class RegistrationFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form_activity);

        startFieldsForm();

    }

    private void startFieldsForm() {
        configFieldName();

        configFieldCpf();

        configFieldTelephone();

        configFieldEmail();

        configFieldPassword();
    }

    private void configFieldPassword() {
        TextInputLayout inputLayoutPassword = findViewById(R.id.registration_form_password);
        checkFieldForm(inputLayoutPassword);
    }

    private void configFieldEmail() {
        TextInputLayout inputLayoutEmail = findViewById(R.id.registration_form_email);
        checkFieldForm(inputLayoutEmail);
    }

    private void configFieldTelephone() {
        TextInputLayout inputLayoutTelephone = findViewById(R.id.registration_form_telephone);
        checkFieldForm(inputLayoutTelephone);
    }

    private void configFieldCpf() {
        final TextInputLayout inputLayoutCpf = findViewById(R.id.registration_form_cpf);
        final EditText fieldCpf = inputLayoutCpf.getEditText();
        final CPFFormatter cpfFormatter = new CPFFormatter();

        fieldCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cpf = fieldCpf.getText().toString();
                if (!hasFocus) {
                    if (!confirmRequiredField(cpf, inputLayoutCpf)) return;
                    if (!confirmRequiredElevenDigits(cpf, inputLayoutCpf)) return;
                    if (!confirmValidCPF(cpf, inputLayoutCpf)) return;
                    removeError(inputLayoutCpf);

                    String cpfFormat = cpfFormatter.format(cpf);
                    fieldCpf.setText(cpfFormat);

                }else {
                    try {
                        String cpfUnformatted = cpfFormatter.unformat(cpf);
                        fieldCpf.setText(cpfUnformatted);
                    }catch (IllegalArgumentException e){
                        Log.e("erro formatação cpf", e.getMessage());
                    }

                }
            }
        });

    }

    private boolean confirmValidCPF(String cpf, TextInputLayout inputLayoutCpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            inputLayoutCpf.setError("CPF inválido");
            return false;
        }
        return true;
    }

    private void removeError(TextInputLayout inputLayout) {
        inputLayout.setError(null);
        inputLayout.setErrorEnabled(false);
    }

    private boolean confirmRequiredElevenDigits(String cpf, TextInputLayout inputLayoutCpf) {
        if (cpf.length() != 11) {
            inputLayoutCpf.setError("CPF precisa ter 11 dígitos");
            return false;
        }
        return true;
    }

    private void configFieldName() {
        TextInputLayout inputLayoutName = findViewById(R.id.registration_form_full_name);
        checkFieldForm(inputLayoutName);
    }

    private void checkFieldForm(final TextInputLayout inputLayoutField) {
        final EditText field = inputLayoutField.getEditText();
        field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String text = field.getText().toString();
                if (!hasFocus) {
                    if (!confirmRequiredField(text, inputLayoutField)) return;
                    removeError(inputLayoutField);
                }
            }
        });
    }

    private boolean confirmRequiredField(String text, TextInputLayout inputLayoutField) {
        if (text.isEmpty()) {
            inputLayoutField.setError("Campo Obrigatório");
            return false;
        }
        return true;

    }
}

