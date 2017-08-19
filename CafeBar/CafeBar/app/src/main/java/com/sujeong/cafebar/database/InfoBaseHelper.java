package com.sujeong.cafebar.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import javax.security.auth.login.LoginException;

/**
 * Created by sujeong on 2017-02-20.
 */

public class InfoBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DB";

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "infoBase.db";
    SQLiteDatabase db;

    public InfoBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: prev");
        db.execSQL("CREATE TABLE InfoTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, METHOD TEXT);");
        Log.e(TAG, "onCreate: after");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String name, String method) {
        db = getWritableDatabase();
        db.execSQL("INSERT INTO InfoTable (NAME, METHOD) VALUES('" + name + "', '" + method  + "');");
        db.close();
    }

    public void update(String name, String method) {
        db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE InfoTable SET Method=" + method + " WHERE InfoName='" + name + "';");
        db.close();
    }

    public void delete(String name) {
        db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM InfoTable WHERE InfoName='" + name + "';");
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM InfoTable", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + "\n";
        }

        return result;
    }

    public String getMethod(String name) {
        // 읽기가 가능하게 DB 열기
        db = getReadableDatabase();

        String result="";
        Cursor cursor = db.rawQuery("SELECT * FROM InfoTable", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(name)) {
                result += cursor.getString(2);
                Log.e(TAG, "getMethod: "+result);
                break;
            }
        }
        return result;
    }
}
