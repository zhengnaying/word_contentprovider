package com.example.word_contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="myWordsTag";
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = getContentResolver();
        Button buttonAll = (Button) findViewById(R.id.all);
        Button buttonInsert = (Button) findViewById( R.id.insert);
        Button buttonDelete = (Button) findViewById(R.id.delete);
        Button buttonDeleteAll = (Button) findViewById(R.id.deleteAll);
        Button buttonUpdate = (Button) findViewById(R.id.update);
        Button buttonSearch = (Button) findViewById(R.id.search);

        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = resolver.query(Words.Word.CONTENT_URI,new String[]{Words.Word._ID,Words.Word.COLUMN_NAME_WORD,Words.Word.COLUMN_NAME_MEANING,Words.Word.COLUMN_NAME_SAMPLE},null,null,null);
                if(c == null){
                    Toast.makeText(MainActivity.this,"没有找到数据",Toast.LENGTH_LONG).show();
                    return;
                }
                String msg="";
                if(c.moveToFirst()){
                    do{
                        msg += "ID : " + c.getString(c.getColumnIndex(Words.Word._ID)) +",";
                        msg += "单词:  " +c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_WORD))+",";
                        msg += "含义: " +c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_MEANING))+",";
                        msg+= "例句: "+ c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE))+"\n";

                    }while (c.moveToNext());
                }
                Log.v(TAG,msg);
            }
        });

        //增加
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strWord="Banana";
                String strMeaning="banana";
                String strSample="This banana is very nice.";
                ContentValues values = new ContentValues();

                values.put(Words.Word._ID,"3");
                values.put(Words.Word.COLUMN_NAME_WORD, strWord);
                values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
                values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

                Uri newUri = resolver.insert(Words.Word.CONTENT_URI, values);
            }
        });

        //删除
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id="3";//简单起见，这里指定ID，用户可在程序中设置id的实际值
                Uri uri = Uri.parse(Words.Word.CONTENT_URI_STRING);
                int result = resolver.delete(uri, Words.Word._ID+"="+id, null);
            }
        });

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolver.delete(Words.Word.CONTENT_URI,null,null);
            }
        });

        //修改
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id="3";
                String strWord="Banana";
                String strMeaning="香蕉";
                String strSample="This banana is very nice.";
                ContentValues values = new ContentValues();

                values.put(Words.Word._ID,id);
                values.put(Words.Word.COLUMN_NAME_WORD, strWord);
                values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
                values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

                 resolver.update(Words.Word.CONTENT_URI, values, Words.Word._ID+"="+id, null);

            }
        });

        //根据id查询
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id="3";
                Cursor cursor = resolver.query(Words.Word.CONTENT_URI,
                        null,
                        Words.Word._ID+"="+id, null, null);


                //找到记录，这里简单起见，使用Log输出

                String msg = "";
                if (cursor.moveToFirst()){
                    do{
                        msg += "ID:" + cursor.getInt(cursor.getColumnIndex(Words.Word._ID)) + ",";
                        msg += "单词：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_WORD))+ ",";
                        msg += "含义：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_MEANING)) + ",";
                        msg += "示例" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE)) + "\n";
                    }while(cursor.moveToNext());
                }
                if (msg == ""){
                    Toast.makeText(MainActivity.this,"没有找到记录",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                Log.v(TAG,msg);
            }
        });
    }

}