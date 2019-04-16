package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Rejestracja extends AppCompatActivity {
    EditText txtUsername,txtPass,txtMail;
    CheckBox cBoxR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        txtUsername =(EditText)findViewById(R.id.editName);
        txtPass =(EditText)findViewById(R.id.editPassword);
        txtMail=(EditText)findViewById(R.id.editEmail);
        cBoxR = (CheckBox)findViewById(R.id.cBoxR);
        cBoxR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    txtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    txtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    public void OnReg(View view){
        String username,password,email;
        username = txtUsername.getText().toString();
        password = txtPass.getText().toString();
        email = txtMail.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password,email);
    }
    public void SignIn(View view) {
        startActivity(new Intent(this,Logowanie.class));
    }
}
