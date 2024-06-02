package com.example.hoc10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddNoticeActivity extends AppCompatActivity implements View.OnClickListener{
    public Spinner spt;
    private EditText etTitle,etPrice,etDate,etStatus;
    private Button btCreate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        initView();
        btCreate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        etDate.setOnClickListener(this);
    }

    private void initView() {
        spt=findViewById(R.id.sptCategory);
        etTitle=findViewById(R.id.tvtTitle);
        etPrice=findViewById(R.id.tvtPrice);
        etDate=findViewById(R.id.tvtDate);
        etStatus=findViewById(R.id.tvtStatus);
        btCreate=findViewById(R.id.bttCreate);
        btCancel=findViewById(R.id.bttCancel);
        spt.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if(view==etDate) {
            final Calendar c = Calendar.getInstance();
            int year= c.get(Calendar.YEAR);
            int month= c.get(Calendar.MONTH);
            int day= c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog= new DatePickerDialog(AddNoticeActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                    etDate.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(view==btCancel){
            finish();
        }
        if(view==btCreate) {
            String t = etTitle.getText().toString();
            String p= etPrice.getText().toString();
            String c=spt.getSelectedItem().toString();
            String d=etDate.getText().toString();
            String s=etStatus.getText().toString();
            if(!t.isEmpty() && p.matches("\\d+")) {
                Notice n= new Notice(t,c,p,d,s);
                SQLiteHelp db= new SQLiteHelp(this);
                db.addNotice(n);
                finish();
            }
        }
   }
}