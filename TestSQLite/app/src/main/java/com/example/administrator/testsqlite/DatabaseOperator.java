package com.example.administrator.testsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/26.
 * 数据库操作类， 负责 添加 ，删除 ，更新 ，查询，数据
 */
public class DatabaseOperator {
    private SQLiteDatabase db;

    /************
     * 构造函数,
     ******************/
    public DatabaseOperator(InfoDatabase infoDatabase) {
        this.db = infoDatabase.getWritableDatabase();//创建数据表，并返回数据库对象
    }

    /************
     * 添加数据,向表Info插入一条info
     ******************/
    public void insertToInfo(Info info) {
        db.beginTransaction();//开启事务
        try {
            ContentValues values = new ContentValues();
            //装填数据
            values.put("brand", info.getbrand());
            values.put("type", info.gettype());
            //插入数据到表Info
            db.insert("Info", null, values);
            db.setTransactionSuccessful();//事务成功
        } catch (Exception e) {
            Log.e("AAA", "插入事务失败");
            e.printStackTrace();
        } finally {
            db.endTransaction();//结束事务
        }
    }

    /************
     * 删除数据,向表Info插入一条info
     ******************/
    public void deleteFromInfo() {
        db.beginTransaction();//开启事务
        try {
            db.delete("Info", null, null);//删除所有数据
            db.setTransactionSuccessful();//事务成功
        } catch (Exception e) {
            Log.e("AAA", "删除事务失败");
            e.printStackTrace();
        } finally {
            db.endTransaction();//结束事务
        }
    }


    /************
     * 更新数据,更新id，为新info
     ******************/
    public void updateFromInfo(Info info, int id) {
        db.beginTransaction();//开启事务
        try {
            ContentValues values = new ContentValues();
            //装填数据
            values.put("brand", info.getbrand());
            values.put("type", info.gettype());
            //更新数据
            String[] s = new String[1];
            s[0] = String.valueOf(id);
            db.update("Info", values, "id=?", s);
            db.setTransactionSuccessful();//事务成功
        } catch (Exception e) {
            Log.e("AAA", "更新事务失败");
            e.printStackTrace();
        } finally {
            db.endTransaction();//结束事务
        }
    }

    /************
     * 查询数据，返回info的list
     ******************/
    public List<Info> queryFromInfo() {
        List<Info> queryResult = new ArrayList<Info>();//实例Info的容器
        db.beginTransaction();//开启事务
        try {
            Cursor cursor = db.query("Info", null, null, null, null, null, null);//查询数据
            if (cursor.moveToFirst()) {
                do {
                    Info querydata = new Info();//查询到的每个数据
                    querydata.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    querydata.setbrand(cursor.getString(cursor.getColumnIndex("brand")));
                    querydata.settype(cursor.getString(cursor.getColumnIndex("type")));
                    queryResult.add(querydata);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.setTransactionSuccessful();//事务成功
        } catch (Exception e) {
            Log.e("AAA", "查询事务失败");
            e.printStackTrace();
        } finally {
            db.endTransaction();//结束事务
        }
        return queryResult;
    }
}
