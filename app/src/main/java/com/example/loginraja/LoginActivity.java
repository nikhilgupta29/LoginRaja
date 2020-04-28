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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email,passw;
    Button signin;
    TextView not_register;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.lemail);
        passw = findViewById(R.id.lpass);
        signin = findViewById(R.id.signin);
        not_register = findViewById(R.id.textView);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null)
                {
                    Toast.makeText(LoginActivity.this, "You are Loggged In.... Enjoy !!!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }
                else
                    {
                        Toast.makeText(LoginActivity.this, "Please Login Again.... ", Toast.LENGTH_SHORT).show();
                    }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pss = passw.getText().toString();

                if(mail.isEmpty())
                {
                    email.setError("Please Enter an Email !!!!");
                    email.requestFocus();
                }
                else if(pss.isEmpty())
                {
                    passw.setError("Password is Required !!!");
                    passw.requestFocus();
                }
                else if(mail.isEmpty() && pss.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fields are Empty !!!!", Toast.LENGTH_LONG).show();
                }
                else if(!(mail.isEmpty() && pss.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(mail, pss).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                FirebaseAuthException exception = (FirebaseAuthException)task.getException();
                                Toast.makeText(LoginActivity.this, "Login Error.... Please try Again !!!! "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(LoginActivity.this, "Login Successful !!!!! ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Error Occurred !!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        not_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,StartPage.class));
            }
        });

    }
}



