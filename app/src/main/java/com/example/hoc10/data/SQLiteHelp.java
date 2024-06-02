package com.example.hoc10.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hoc10.model.Notice;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Thongbao.db";
    private static int DATABASE_VERSION= 1 ;
    public SQLiteHelp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE notices("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT,category TEXT,price TEXT,date TEXT,status TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    public List<Notice> getAll1(){
        List<Notice> list = new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query("notices",null,null,null,
                null,null,order);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category= rs.getString(2);
            String price= rs.getString(3);
            String date= rs.getString(4);
            String status=rs.getString(5);
            list.add(new Notice(id,title,category,price,date,status));
        }
        return list;
    }
    public long addNotice(Notice n) {
        ContentValues values = new ContentValues();
        values.put("title",n.getTitle());
        values.put("category",n.getCategory());
        values.put("price",n.getPrice());
        values.put("date",n.getDate());
        values.put("status",n.getStatus());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("notices",null,values);
    }
    public List<Notice> getByDate(String date) {
        List<Notice> list = new ArrayList<>();
        String whereClause = "date like ?";
        String[] whereArgs={date};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs= st.query("notices",null,whereClause,whereArgs,
                null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category= rs.getString(2);
            String price= rs.getString(3);
            String status =rs.getString(5);
            list.add(new Notice(id,title,category,price,date,status));
        }
        return list;
    }
    public List<Notice> getByStatus(String status) {
        List<Notice> list = new ArrayList<>();
        String whereClause = "status like ?";
        String[] whereArgs={status};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs= st.query("notices",null,whereClause,whereArgs,
                null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category= rs.getString(2);
            String price= rs.getString(3);
            String date=rs.getString(4);
            list.add(new Notice(id,title,category,price,date,status));
        }
        return list;
    }
    public int updateNotice(Notice n) {
        ContentValues values = new ContentValues();
        values.put("title",n.getTitle());
        values.put("category",n.getCategory());
        values.put("price",n.getPrice());
        values.put("date",n.getDate());
        values.put("status",n.getStatus());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs={Integer.toString(n.getId())};
        return sqLiteDatabase.update("notices",values,whereClause,whereArgs);
    }
    public int deleteNotice(int id) {
        String whereClause = "id=?";
        String[] whereArgs={Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("notices",whereClause,whereArgs);
    }
    public List<Notice> searchByDate(String from,String to) {
        List<Notice> list = new ArrayList<>();
        String whereClause = "date BETWEEN?AND?";
        String[] whereArgs={from.trim(),to.trim()};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs= st.query("notices",null,whereClause,whereArgs,
                null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category= rs.getString(2);
            String price= rs.getString(3);
            String date=rs.getString(4);
            String status=rs.getString(5);
            list.add(new Notice(id,title,category,price,date,status));
        }
        return list;
    }
    public List<Notice> searchByStatus(String key) {
        List<Notice> list = new ArrayList<>();
        String whereClause = "status like ?";
        String[] whereArgs={"%"+key+"%"};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs= st.query("notices",null,whereClause,whereArgs,
                null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category= rs.getString(2);
            String price= rs.getString(3);
            String date=rs.getString(4);
            String status=rs.getString(5);
            list.add(new Notice(id,title,category,price,date,status));
        }
        return list;
    }
}
