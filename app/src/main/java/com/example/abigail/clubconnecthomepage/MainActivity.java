package com.example.abigail.clubconnecthomepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //MainActivity links to loginpage
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage); // loginpage is the id for the homepage

        final FirebaseAuth auth = FirebaseAuth.getInstance(); // get firebase auth instance
     //If username is valid, proceed with the login
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
     //Links buttons
        final EditText inputEmail = (EditText) findViewById(R.id.email);
        final EditText inputPassword = (EditText) findViewById(R.id.password);
        final Button btnSignup = (Button) findViewById(R.id.btn_signup);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button btnReset = (Button) findViewById(R.id.btn_reset_password);

     //If user wishes to signup, go to activity_signup
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });

     //If user wishes to reset password, go to activity_reset_password
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //resetPassword();
                startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
            }
        });

     //Actions to collect user input in order to login
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
     //Tells user to input email address if there is invalid input
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
     //Tells user to input password if there is invalid input
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, Main_Page.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}







