package com.example.hoc10.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hoc10.model.Account;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelpest extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Taikhoan.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelpest(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE accounts(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT,password TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add upgrade logic here if needed
    }

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("accounts", null, null, null,
                null, null, "id DESC");

        if (cursor != null && cursor.moveToNext()) {
            do {
                int id = cursor.getInt(0);
                String username= cursor.getString(1);
                String password= cursor.getString(2);
                accountList.add(new Account(id, username, password));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return accountList;
    }

    public long addAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("accounts", null, values);
    }

    public boolean authenticate(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("accounts", null, selection, selectionArgs,
                null, null, null);

        boolean authenticated = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return authenticated;
    }

    public int updateAccount(Account account) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(account.getId())};
        return db.update("accounts", values, whereClause, whereArgs);
    }
    public int deleteAccount(int id) {
        String whereClause = "id=?";
        String[] whereArgs={Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("accounts",whereClause,whereArgs);
    }
    public List<Account> searchByUsername(String Username) {
        List<Account> list = new ArrayList<>();
        String whereClause = "username like ?";
        String[] whereArgs={Username};
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs= st.query("accounts",null,whereClause,whereArgs,
                null,null,null);
        while(rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String u = rs.getString(1);
            String p= rs.getString(2);
            list.add(new Account(id,u,p));
        }
        return list;
    }
}
