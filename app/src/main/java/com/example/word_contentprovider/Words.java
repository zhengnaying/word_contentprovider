package com.example.word_contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Words {
    public static final String AUTHORITY = "com.example.wordsbook";//URI授权者
    public static abstract class Word implements BaseColumns {
        public static final  String TABLE_NAME = "words";   //表名
        public static final String COLUMN_NAME_WORD = "word";   //字段：单词
        public static final String COLUMN_NAME_MEANING = "meaning"; //字段： 单词含义
        public static final String COLUMN_NAME_SAMPLE = "sample"; //字段： 单词示例


        public static final String PATH_MULTIPLE = "words";//多条数据的路径

        //Content Uri
        public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + Words.Word.TABLE_NAME;
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + Words.Word.TABLE_NAME);

    }
}
