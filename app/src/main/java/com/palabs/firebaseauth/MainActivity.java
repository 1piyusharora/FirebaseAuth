package com.palabs.firebaseauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name,email,password;
    Button buttonRegister,buttonLogin;
    FirebaseAuth auth;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        buttonRegister=(Button)findViewById(R.id.buttonRegiser);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);

        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

        auth=FirebaseAuth.getInstance();
        user=new User();
    }

    void registerUser(){

    auth.createUserWithEmailAndPassword(user.email,user.password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){

                String uid=task.getResult().getUser().getUid();
                Toast.makeText(MainActivity.this,user.name+" Registered Successfully",Toast.LENGTH_LONG).show();
                Log.i("User","User Registered : "+uid);
            }
        }
    })
            .addOnFailureListener(this, new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Log.i("User","User Registeration Failed : "+e.getMessage());
            e.printStackTrace();
        }
    });

    }


    void loginUser(){

        auth.signInWithEmailAndPassword(user.email,user.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String uid=task.getResult().getUser().getUid();
                    Toast.makeText(MainActivity.this,user.name+" Logged In Successfully",Toast.LENGTH_LONG).show();
                    Log.i("User","User Logged In : "+uid);

                    Intent intent=new Intent(MainActivity.this,Welcome.class);
                    startActivity(intent);
                    finish();
                }
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("User","User Login Failed : "+e.getMessage());
                        e.printStackTrace();
                    }
                });

    }


    @Override
    public void onClick(View view) {

        if(view==buttonRegister){
            user.name=name.getText().toString().trim();
            user.email=email.getText().toString().trim();
            user.password=password.getText().toString().trim();

            registerUser();
        }
        if(view==buttonLogin){
            user.name=name.getText().toString().trim();
            user.email=email.getText().toString().trim();
            user.password=password.getText().toString().trim();

            loginUser();
        }

    }
}
