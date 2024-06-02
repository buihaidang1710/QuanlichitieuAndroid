package com.example.hoc10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hoc10.data.SQLiteHelp;
import com.example.hoc10.model.Notice;

import java.util.Calendar;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner sp;
    private EditText eTitle,ePrice,eDate,eStatus;
    private Button btUpdate,btDelete,btCancel;
    private Notice notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
        btDelete.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent=getIntent();
        notice=(Notice)intent.getSerializableExtra("notice1");
        eTitle.setText(notice.getTitle());
        ePrice.setText(notice.getPrice());
        eDate.setText(notice.getDate());
        eStatus.setText(notice.getStatus());
        int p=0;
        for (int i=0;i<sp.getCount();i++) {
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(notice.getCategory())) {
                p=i;
                break;
            }
        }
        sp.setSelection(p);
    }

    private void initView() {
        sp=findViewById(R.id.spCategory1);
        eTitle=findViewById(R.id.tvTitle1);
        ePrice=findViewById(R.id.tvPrice1);
        eDate=findViewById(R.id.tvDate1);
        eStatus=findViewById(R.id.tvStatus1);
        btDelete=findViewById(R.id.btDelete1);
        btCancel=findViewById(R.id.btCancel1);
        btUpdate=findViewById(R.id.btUpdate1);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelp db=new SQLiteHelp(this);
        if(view==eDate) {
            final Calendar c = Calendar.getInstance();
            int year= c.get(Calendar.YEAR);
            int month= c.get(Calendar.MONTH);
            int day= c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog= new DatePickerDialog(DeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(d>9){
                        date=d+"/";
                    }else {
                        date="0"+d+"/";
                    }
                    if(m>8) {
                        date+=(m+1)+"/";
                    }else {
                        date+="0"+(1+m)+"/";
                    }
                    date+=y;
                    eDate.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(view==btCancel) {
            finish();;
        }
        if(view==btUpdate) {
            String t = eTitle.getText().toString();
            String p= ePrice.getText().toString();
            String c=sp.getSelectedItem().toString();
            String d=eDate.getText().toString();
            String s=eStatus.getText().toString();
            if(!t.isEmpty() && p.matches("\\d+")) {
                int id =notice.getId();
                Notice n= new Notice(id,t,c,p,d,s);
                db= new SQLiteHelp(this);
                db.updateNotice(n);
                finish();
            }
        }
        if(view==btDelete) {
            int id= notice.getId();
            AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa thong bao "+notice.getTitle()+" nay khong");
            builder.setIcon(R.drawable.img);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelp db= new SQLiteHelp(getApplicationContext());
                    db.deleteNotice(id);
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