package com.kelompok3.rplinformatika.informaticslibrary.Dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelompok3.rplinformatika.informaticslibrary.Bantuan.BantuanActivity;
import com.kelompok3.rplinformatika.informaticslibrary.BookActivity.MainActivityBook;
import com.kelompok3.rplinformatika.informaticslibrary.JurnalActivity.MainActivityJurnal;
import com.kelompok3.rplinformatika.informaticslibrary.LoginAndRegister.LoginActivity;
import com.kelompok3.rplinformatika.informaticslibrary.R;

public class DashBoard extends AppCompatActivity {

    private CardView cariBuku;
    private CardView cariJurnal;
    private CardView Bantuan;
    private CardView Keluar;

    private TextView textViewUser;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null ) {
            finish();
            Intent i = new Intent(DashBoard.this, LoginActivity.class);
            startActivity(i);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUser = findViewById(R.id.user);
        textViewUser.setText(user.getEmail());

        cariBuku=findViewById(R.id.buttoncaribuku);
        cariBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityBook.class);
                startActivity(i);
            }
        });

        cariJurnal = findViewById(R.id.buttoncarijurnal);
        cariJurnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityJurnal.class);
                startActivity(i);
            }
        });

        Keluar = findViewById(R.id.buttonkeluar);
        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent i = new Intent(DashBoard.this, LoginActivity.class);
                startActivity(i);
            }
        });

        Bantuan = findViewById(R.id.buttonbantuan);
        Bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BantuanActivity.class);
                startActivity(i);
            }
        });
    }
}
