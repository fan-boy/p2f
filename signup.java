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


public class signup extends AppCompatActivity {

    EditText userrname,password;
    AutoCompleteTextView email;
    Button signup;
    TextView login;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance();
        userrname = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password1);
        email = (AutoCompleteTextView) findViewById(R.id.email1);
        signup = (Button)findViewById(R.id.bsignup);
        login = (TextView)findViewById(R.id.login);
        progressbar = (ProgressBar)findViewById(R.id.progressBar);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString().trim();
                String passwords = password.getText().toString().trim();
                String usernames= userrname.getText().toString().trim();

                if (usernames.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (emails.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter Email Address!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (passwords.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(emails,passwords)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signup.this,"Registeration Succesfull" + task.isSuccessful(),Toast.LENGTH_LONG).show();
                                progressbar.setVisibility(View.GONE);
                                if(!task.isSuccessful()){
                                    Toast.makeText(signup.this,"Registration Failed",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    startActivity(new Intent(signup.this,Login.class));
                                    finish();
                                }

                            }
                        });
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signup.this,Login.class);
                startActivity(i);
            }
        });
    }
}
