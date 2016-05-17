package com.sur.ultra.contacta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexis on 5/16/16.
 */
public class BaseHelper extends SQLiteOpenHelper {
    String table = "CREATE TABLE Mensajes(Id INTEGER PRIMARY KEY AUTOINCREMENT, Content Text)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
