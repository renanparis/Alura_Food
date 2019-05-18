package com.renanparis.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.renanparis.alurafood.R;

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
        TextInputLayout inputLayoutCpf = findViewById(R.id.registration_form_cpf);
        checkFieldForm(inputLayoutCpf);
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
                if (!hasFocus){
                    confirmRequiredField(text, inputLayoutField);
                }
            }
        });
    }

    private void confirmRequiredField(String text, TextInputLayout inputLayoutField) {
        if (text.isEmpty()) {
            inputLayoutField.setError("Campo Obrigatório");
        }else{
            inputLayoutField.setError(null);
            inputLayoutField.setErrorEnabled(false);
        }
    }
}

