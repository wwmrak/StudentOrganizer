package com.wwmrak.main.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wwmrak.studentorganizer.R;

public class LoginActivity extends Activity {

    Button buttonLogin, buttonCancel;
    EditText usernameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button) findViewById(R.id.button);
        buttonCancel = (Button) findViewById(R.id.button2);

        usernameText = (EditText) findViewById(R.id.editText);
        passwordText = (EditText) findViewById(R.id.editText2);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameText.getText().toString().equals("admin") &&
                        passwordText.getText().toString().equals("admin")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Non existing account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
