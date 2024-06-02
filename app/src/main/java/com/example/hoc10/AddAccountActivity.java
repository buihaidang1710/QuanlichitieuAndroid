package com.example.hoc10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoc10.data.SQLiteHelpest;
import com.example.hoc10.model.Account;

public class AddAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eUsername,ePass,eCpass;
    private Button eConfirm,eCancel;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        eConfirm.setOnClickListener(this);
        eCancel.setOnClickListener(this);
//        Intent intent2= getIntent();
//        account=(Account)intent2.getSerializableExtra("account3");
    }

    private void initView() {
        eUsername=findViewById(R.id.tvtUsername);
        ePass=findViewById(R.id.tvtPassword);
        eCpass=findViewById(R.id.tvtConfirm);
        eConfirm=findViewById(R.id.bttRegister);
        eCancel=findViewById(R.id.bttCancel);
    }


    @Override
    public void onClick(View view) {
        if(view==eCancel) {
            finish();
        }
        if(view==eConfirm) {
            String u = eUsername.getText().toString();
            String p= ePass.getText().toString();
            String c = eCpass.getText().toString();
            SQLiteHelpest db= new SQLiteHelpest(this);
            if (u.isEmpty() || p.isEmpty() || c.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!p.equalsIgnoreCase(c)) {
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if(p.equalsIgnoreCase(c)){
                Account account= new Account(u,p);
                db.addAccount(account);
                finish();
            } else {
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}