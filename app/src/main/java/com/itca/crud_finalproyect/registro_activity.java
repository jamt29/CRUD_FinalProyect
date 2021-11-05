package com.itca.crud_finalproyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.Map;

public class registro_activity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button mButton;
    
    String name="";
    String email="";
    String password="";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        changeStatusBarColor();
        
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        
        mButton =(Button) findViewById(R.id.mButton);
        
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                
                
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    if(password.length() >=6){
                        registerUser();
                    }
                    else {
                        FancyToast.makeText(registro_activity.this,"La contraseña debe tener al menos 6 caracteres",FancyToast.LENGTH_LONG, FancyToast.WARNING,true).show();
                    }

                }
                else {
                    FancyToast.makeText(registro_activity.this,"Debe completar los campos",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();

                }
                
            }
        });
        
    }

    private void registerUser() {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("pass", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map);
                    FancyToast.makeText(registro_activity.this,"Usuario registrado correctamente",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    Intent i = new Intent(registro_activity.this, HomeActivity.class);
                    startActivity(i);
                    finish();


                }
                else {
                    FancyToast.makeText(registro_activity.this,"No se pudo registrar este usuario",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                }
            }
        });
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


    public void onLoginClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }
    }
