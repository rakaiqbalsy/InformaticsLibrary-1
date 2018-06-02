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
import com.kelompok3.rplinformatika.informaticslibrary.Dashboard.DashBoard;
import com.kelompok3.rplinformatika.informaticslibrary.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            //masuk ke activity Dashboard
            finish();
            Intent i = new Intent(getApplicationContext(), DashBoard.class);
            startActivity(i);
        }

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        buttonLogin = findViewById(R.id.loginButton);

        editTextEmail = findViewById(R.id.loginEmail);
        editTextPassword = findViewById(R.id.loginPassword);

        textViewRegister = findViewById(R.id.textViewDaftar);

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
    }

    private  void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //kalau emailnya kosong pas masuk
            Toast.makeText(this, "Masukan Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Masukan Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Proses Masuk....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            //masuk ke dashboard
                            finish();
                            Intent i = new Intent(getApplicationContext(), DashBoard.class);
                            startActivity(i);
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {

        if(v == buttonLogin) {
            //function login
            userLogin();
        }

        if(v == textViewRegister) {
            finish();
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
        }
    }
}
