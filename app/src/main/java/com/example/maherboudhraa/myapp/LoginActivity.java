package com.example.maherboudhraa.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSingup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new  Intent(getApplicationContext(), Home.class));

        }

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailS);
        editTextPassword = (EditText) findViewById(R.id.editTextPaswordS);
        textViewSingup = (TextView) findViewById(R.id.textViewSignup);
        buttonSignIn.setOnClickListener(this);
        textViewSingup.setOnClickListener(this);

    }
private void userLogin(){
    String email = editTextEmail.getText().toString().trim();
    String password = editTextPassword.getText().toString().trim();
    if (TextUtils.isEmpty(email)){
        Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
        return;
    }
    if  (TextUtils.isEmpty(password)){
        Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
        return;

    }
    progressDialog.setMessage("Conecting...");
    progressDialog.show();
    firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    finish();
                    startActivity(new  Intent(getApplicationContext(), Home.class));

                }
            });

}
    @Override
    public void onClick(View view) {
        if (buttonSignIn == view) {
             userLogin();
        }
        if (view==textViewSingup){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }


}
