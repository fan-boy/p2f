package com.ueuo.helloworld.p2f;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpassword extends AppCompatActivity {
    AutoCompleteTextView email;
    Button reset;
    ProgressBar p;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        reset = (Button)findViewById(R.id.breset);
        email = (AutoCompleteTextView)findViewById(R.id.remail);
        p =(ProgressBar)findViewById(R.id.progressBar2);

        auth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String remail=email.getText().toString().trim();
                if(TextUtils.isEmpty(remail)){
                    Toast.makeText(resetpassword.this,"Enter your registered email",Toast.LENGTH_LONG).show();
                    return;
                }
                p.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(remail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override

                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(resetpassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(resetpassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                p.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

}

