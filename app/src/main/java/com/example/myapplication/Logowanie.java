package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.util.Log;

public class Logowanie extends AppCompatActivity {

    CheckBox cBox;
    EditText txtPass,txtlogin;
    TextView txtSignUp,txtMes;
    Button btnSignIn;
    String login,password,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        txtPass= (EditText) findViewById(R.id.txtPassword);
        txtlogin = (EditText) findViewById(R.id.txtLogin);
        cBox =  (CheckBox) findViewById(R.id.cBox);
        txtSignUp =(TextView) findViewById(R.id.LabelReg);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        txtMes = (TextView) findViewById(R.id.txtMes);




        cBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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

        /*btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    login = txtlogin.getText().toString();
                    password = txtPass.getText().toString();
                    query = new Query();
                    query.start(login,password);
                try
                {
                    query.join();
                }catch (InterruptedException ie)
                {
                    Log.e("pass 0 ", ie.getMessage());
                }
                message=query.getMessage();
                txtMes.setText(message);

            }
        });*/
    }

    public void OnLogin(View view){
        login = txtlogin.getText().toString();
        password = txtPass.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,login,password);
    }
    public void SignUp(View view) {
        startActivity(new Intent(this,Rejestracja.class));
    }
}
