
package com.ueuo.helloworld.p2f;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    AutoCompleteTextView email;
    TextView password;
    Button login;
    TextView register,fpass;
    private FirebaseAuth auth;
    ProgressBar progressbarr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
          startActivity(new Intent(Login.this,Main.class));
        }
        email = (AutoCompleteTextView)findViewById(R.id.email);
        progressbarr=(ProgressBar)findViewById(R.id.progressBarr);
        password = (TextView)findViewById(R.id.password);
        login=(Button)findViewById(R.id.blogin);
        register = (TextView)findViewById(R.id.signup);
        fpass = (TextView)findViewById(R.id.fpass);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,signup.class);
                startActivity(i);
            }
        });
        fpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Login.this,resetpassword.class);
                startActivity(p);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString().trim();
                final String passwords = password.getText().toString().trim();



                if (emails.isEmpty()) {
                    Toast.makeText(Login.this,"Enter Email Address!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (passwords.isEmpty()) {
                    Toast.makeText(Login.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressbarr.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(emails,passwords)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressbarr.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if(passwords.length()<6){
                                        Toast.makeText(Login.this,"Enter propper password",Toast.LENGTH_SHORT).show();
                                        password.setText("");
                                    } else {
                                        Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Login.this,"Login Succesfull",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this, Main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });

    }
}
