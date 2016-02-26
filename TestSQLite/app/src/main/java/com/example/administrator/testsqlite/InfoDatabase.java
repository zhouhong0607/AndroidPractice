package com.example.administrator.testsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/25.
 */
public class InfoDatabase extends SQLiteOpenHelper{
    public static final String CREATE_TABLE_INFO="create table Info("+" id integer primary key autoincrement ,"+" brand text,"+"type text)";//建数据表info
//    private Context mContext;

    public InfoDatabase(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
//        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_INFO);
//        Toast.makeText(mContext,"Create table info success",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {

    }

}
