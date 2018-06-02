package com.kelompok3.rplinformatika.informaticslibrary.LoginAndRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.informaticslibrary.Model.User;
import com.kelompok3.rplinformatika.informaticslibrary.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextNama;
    private EditText editTextPassword;
    private TextView textViewLogin;
    private EditText editTextNo;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase database;
    private DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("user");

        buttonRegister = findViewById(R.id.regiterButton);
        editTextEmail = findViewById(R.id.registerEmail);
        editTextNama = findViewById(R.id.registerNama);
        editTextPassword = findViewById(R.id.registerPassword);
        textViewLogin = findViewById(R.id.textViewLogin);
        editTextNo = findViewById(R.id.registerNo);

        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String no = editTextNo.getText().toString().trim();
        String nama = editTextNama.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //if username is empty
            Toast.makeText(this, "Masukan Email Anda!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            //if password is empty
            Toast.makeText(this, "Masukan Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(nama)) {
            //if nama is empty
            Toast.makeText(this,"Masukan Nama Anda", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(no)) {
            //if nama is empty
            Toast.makeText(this,"Masukan No Anda", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog.setMessage("Mendaftarkan .....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Berhasil Mendaftar..", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Gagal Mendaftar, Silahkan Cek Kembali Form..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

      /*  table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(editTextNo.toString().trim()).exists()) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Email terdaftar", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.dismiss();
                    User user = new User(editTextEmail.toString().trim(),
                            editTextNama.toString().trim(), editTextPassword.toString().trim());
                    table_user.child(editTextEmail.toString().trim()).setValue(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister) {
            // ke register function
            registerUser();
        }

        if(v == textViewLogin) {
            finish();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }
    }
}