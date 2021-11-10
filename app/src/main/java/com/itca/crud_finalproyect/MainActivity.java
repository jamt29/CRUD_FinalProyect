package com.itca.crud_finalproyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
 private EditText editTextEmail;
 private EditText editTextPassword;
    
 private Button mButton; 

 String email="";
 String password=""; 
    
 
 private FirebaseAuth mAuth;
 DatabaseReference mDatabase;
 private Object FirebaseUser;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
          getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
       }
       setContentView(R.layout.activity_main);
     
     mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mButton =(Button) findViewById(R.id.mButtonL);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
     public void onClick(View view) {
              email = editTextEmail.getText().toString();
              password = editTextPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                } else {
                    FancyToast.makeText(MainActivity.this,"Debe completar los campos",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();
                }
            }
        });
    } 
    
    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser = mAuth.getCurrentUser();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                    //Agregar fancy Toast con el Nombre en el si funciona
                } else {
                    FancyToast.makeText(MainActivity.this,"No se pudo iniciar sesion, revise su conexion a internet o comprube los datos ingresados",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }
        });
    }
  
     
   public void onLoginClick(View view) {

        startActivity(new Intent(MainActivity.this, registro_activity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null){
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }

    }
}    
     
     
     
     
     
     
     
