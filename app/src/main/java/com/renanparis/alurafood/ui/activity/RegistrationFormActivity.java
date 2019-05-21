package com.renanparis.alurafood.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.renanparis.alurafood.R;
import com.renanparis.alurafood.formatter.TelephoneDDDFormatter;
import com.renanparis.alurafood.validator.EmailValidator;
import com.renanparis.alurafood.validator.StandardValidator;
import com.renanparis.alurafood.validator.CpfValidator;
import com.renanparis.alurafood.validator.TelephoneValidator;

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
        configNameField();
        configCpfField();
        configTelephoneField();
        configEmailField();
        configPasswordField();
    }

    private void configPasswordField() {
        TextInputLayout inputLayoutPassword = findViewById(R.id.registration_form_password);
        checkFormField(inputLayoutPassword);
    }

    private void configEmailField() {
        TextInputLayout inputLayoutEmail = findViewById(R.id.registration_form_email);
        final EditText emailField = inputLayoutEmail.getEditText();
        final EmailValidator emailValidator = new EmailValidator(inputLayoutEmail);
        emailField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    emailValidator.isValidEmail();
                }
            }
        });
    }

    private void configTelephoneField() {
        TextInputLayout inputLayoutTelephone = findViewById(R.id.registration_form_telephone);
        final EditText telephoneField = inputLayoutTelephone.getEditText();
        final TelephoneValidator phoneValidator = new TelephoneValidator(inputLayoutTelephone);
        final TelephoneDDDFormatter formatter = new TelephoneDDDFormatter();
        telephoneField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telephone = telephoneField.getText().toString();
                if (hasFocus){
                    String unformattedPhone = formatter.removePhoneFormat(telephone);
                    telephoneField.setText(unformattedPhone);

                }else {
                    phoneValidator.isValid();
                }
            }
        });
    }


    private void configCpfField() {
        final TextInputLayout inputLayoutCpf = findViewById(R.id.registration_form_cpf);
        final EditText cpfField = inputLayoutCpf.getEditText();
        final CPFFormatter cpfFormatter = new CPFFormatter();
        final CpfValidator validator = new CpfValidator(inputLayoutCpf);
        cpfField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    removeCpfFormat(cpfFormatter, cpfField);

                } else {
                    validator.isValidCpf();

                }
            }
        });

    }

    private void removeCpfFormat(CPFFormatter cpfFormatter, EditText fieldCpf) {
        String cpf = fieldCpf.getText().toString();
        try {
            String cpfUnformatted = cpfFormatter.unformat(cpf);
            fieldCpf.setText(cpfUnformatted);
        } catch (IllegalArgumentException e) {
            Log.e(ERROR_FORMAT_CPF, e.getMessage());
        }
    }

    private void configNameField() {
        TextInputLayout inputLayoutName = findViewById(R.id.registration_form_full_name);
        checkFormField(inputLayoutName);
    }

    private void checkFormField(final TextInputLayout inputLayoutField) {
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