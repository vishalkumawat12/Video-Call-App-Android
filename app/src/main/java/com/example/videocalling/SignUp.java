package com.example.videocalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.videocalling.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
FirebaseAuth auth;
ActivitySignUpBinding binding;
FirebaseFirestore  firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModel=new UserModel(binding.name.getText().toString(),binding.email.getText().toString(),binding.pass.getText().toString());
                auth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(SignUp.this, "Account is created", Toast.LENGTH_SHORT).show();
                                    firestore.collection("Users").document().set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                          startActivity(new Intent(SignUp.this,MainActivity.class));
                                        }
                                    });

                                }else {
                                    Toast.makeText(SignUp.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }
}