package com.example.administrator.testsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private InfoDatabase infoDatabase;
    private TextView show_database;
    private List<Info> infoList=new ArrayList<Info>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_database = (TextView) findViewById(R.id.show_database);
        infoDatabase = new InfoDatabase(this, "AutoReport.db", null, 1);//创建数据库AutoReport
        final DatabaseOperator databaseOperator=new DatabaseOperator(infoDatabase);//获取数据库操作对象
        /********************添加数据到Infolist**************************/
        Button createDatabase = (Button) findViewById(R.id.create_db);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                infoDatabase.getWritableDatabase();
                Info info=new Info();
                info.setbrand("Huawei");
                info.settype("Hornor");
                infoList.add(info);

            }
        });
        /********************添加数据**************************/
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              databaseOperator.insertToInfo(infoList.get(infoList.size()-1));//将Infolist最后一个数据插入到数据库
                Toast.makeText(getApplicationContext(), "add data success", Toast.LENGTH_SHORT).show();
            }
        });

        /********************删除数据**************************/
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             databaseOperator.deleteFromInfo();//删除数据库所有数据
                Toast.makeText(getApplicationContext(), "delete data success", Toast.LENGTH_SHORT).show();
            }
        });
        /********************修改数据**************************/
        Button updatedata = (Button) findViewById(R.id.update_data);
        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info info=new Info();
                info.setbrand("ZTE");
                info.settype("Nubia");
              databaseOperator.updateFromInfo(info,1);//修改id为1的数据
                Toast.makeText(getApplicationContext(), "update data success", Toast.LENGTH_SHORT).show();
            }
        });
        /********************替换数据--事务使用**************************/
        Button replacedata = (Button) findViewById(R.id.replace_data);
        replacedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SQLiteDatabase db = infoDatabase.getWritableDatabase();
//
//                db.beginTransaction();//开启事务
//                try {
//                    db.delete("Info", null, null);
////                    if (true) {
////                        //手动抛出一个异常，让事务失败
////                        throw new NullPointerException();
////                    }
//                    ContentValues values = new ContentValues();
//                    values.put("brand", "ZTE");
//                    values.put("type", "Q802T");
//                    db.insert("Info", null, values);
//                    db.setTransactionSuccessful();//事务执行成功
//                    Toast.makeText(getApplicationContext(), "replace data success", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "replace data failed", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                } finally {
//                    db.endTransaction();//结束事务
//                }

            }
        });
        /********************查询数据**************************/
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_database.setText("");
                infoList=databaseOperator.queryFromInfo();

                    for(int i=0;i<infoList.size();i++)
                    {
                        String s = "";
                        s +="Id"+infoList.get(i).getId() + "  brand"+infoList.get(i).getbrand() + "  type"+infoList.get(i).gettype()  + "\n";
                        show_database.append(s);
                    }
                Toast.makeText(getApplicationContext(), "query data success", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
