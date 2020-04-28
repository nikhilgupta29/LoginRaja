package com.example.loginraja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class StartPage extends AppCompatActivity {

    EditText mailId,pass;
    Button register;
    TextView signup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        mailId = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        signup = findViewById(R.id.textView);
        register = findViewById(R.id.register);
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mailId.getText().toString();
                String pss = pass.getText().toString();

                if(email.isEmpty())
                {
                 mailId.setError("Please Enter an Email !!!!");
                 mailId.requestFocus();
                }
                else if(pss.isEmpty())
                {
                    pass.setError("Password is Required !!!");
                    pass.requestFocus();
                }
                else if(email.isEmpty() && pss.isEmpty())
                {
                    Toast.makeText(StartPage.this, "Fields are Empty !!!!", Toast.LENGTH_LONG).show();
                }
                else if(pss.length() < 6 )
                {
                 pass.setError("Password should be atleast of 6 characters !!!!! ");
                 pass.requestFocus();
                }
                else if(!(email.isEmpty() && pss.isEmpty()))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,pss).addOnCompleteListener(StartPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(StartPage.this, "Registration Successful...User added Perfecttly !!!!!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(StartPage.this,LoginActivity.class));

                            }
                            else
                            {
                                FirebaseAuthException exception = (FirebaseAuthException)task.getException();
                                Toast.makeText(StartPage.this, "Registration Failed.... PLease Try Again later !!!!"+exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(StartPage.this, "Error Occurred !!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(StartPage.this,LoginActivity.class));
            }
        });

    }
}
