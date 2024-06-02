package com.example.hoc10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoc10.data.SQLiteHelpest;
import com.example.hoc10.model.Account;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText tUsername,tPass;
    private Button btChange,btCancel,btDelete;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        btChange.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        Intent intent= getIntent();
        account=(Account) intent.getSerializableExtra("account1");
        tUsername.setText(account.getUsername());
        tPass.setText(account.getPassword());
    }

    private void initView() {
        tUsername=findViewById(R.id.tvtUsername);
        tPass=findViewById(R.id.tvtPassword);
        btChange=findViewById(R.id.bttChange);
        btDelete=findViewById(R.id.bttDelete);
        btCancel=findViewById(R.id.bttCancel);
    }

    @Override
    public void onClick(View view) {
        SQLiteHelpest db=new SQLiteHelpest(this);
        if(view==btCancel) {
           finish();
        }
        if(view==btChange) {
            String u = tUsername.getText().toString();
            String p= tPass.getText().toString();
            if(!u.isEmpty() && !p.isEmpty()) {
                int id =account.getId();
                Account account1= new Account(id,u,p);
                db.updateAccount(account1);
                finish();
            }
        }
        if(view==btDelete) {
            int id= account.getId();
            AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa nguoi dung "+account.getUsername()+" khong");
            builder.setIcon(R.drawable.img);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelpest db= new SQLiteHelpest(getApplicationContext());
                    db.deleteAccount(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}