package com.renanparis.alurafood.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.renanparis.alurafood.R;
import com.renanparis.alurafood.validator.StandardValidator;
import com.renanparis.alurafood.validator.ValidatorCpf;

import br.com.caelum.stella.format.CPFFormatter;

public class RegistrationFormActivity extends AppCompatActivity {

    public static final String ERROR_FORMAT_CPF = "erro formatação cpf";

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
        final ValidatorCpf validator = new ValidatorCpf(inputLayoutCpf);
        fieldCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    addCpfFormat(cpfFormatter, fieldCpf);

                } else {
                    validator.isValidCpf();

                }
            }
        });

    }

    private void addCpfFormat(CPFFormatter cpfFormatter, EditText fieldCpf) {
        String cpf = fieldCpf.getText().toString();
        try {
            String cpfUnformatted = cpfFormatter.unformat(cpf);
            fieldCpf.setText(cpfUnformatted);
        } catch (IllegalArgumentException e) {
            Log.e(ERROR_FORMAT_CPF, e.getMessage());
        }
    }

    private void configFieldName() {
        TextInputLayout inputLayoutName = findViewById(R.id.registration_form_full_name);
        checkFieldForm(inputLayoutName);
    }

    private void checkFieldForm(final TextInputLayout inputLayoutField) {
        final EditText field = inputLayoutField.getEditText();
        final StandardValidator validator = new StandardValidator(inputLayoutField);
        field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });
    }

}