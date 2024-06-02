package com.example.hoc10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoc10.adapter.RecycleViewAdapter2;
import com.example.hoc10.data.SQLiteHelpest;
import com.example.hoc10.model.Account;

public class MainActivity1 extends AppCompatActivity{
    private RecycleViewAdapter2 adapter;
    private Button btLogin,btRegister;
    private EditText eUser,ePass;
    private SQLiteHelpest db;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        btLogin=findViewById(R.id.iBT);
        btRegister=findViewById(R.id.iTG);
        eUser=findViewById(R.id.iUS);
        ePass=findViewById(R.id.iPW);
        db = new SQLiteHelpest(this);
        Intent intent= getIntent();
        account=(Account)intent.getSerializableExtra("account2");
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = eUser.getText().toString();
                String ss1 = ePass.getText().toString();
                if (ss.isEmpty() || ss1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if(db.authenticate(ss,ss1)) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MainActivity1.this,MainActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Username or password invalid", Toast.LENGTH_SHORT).show();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity1.this,AddAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}