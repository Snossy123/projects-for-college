package com.example.quizapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainDb extends SQLiteOpenHelper {
    // initial db
    private static final String DataBaseName= "QuizAppDB";
    private static final int DataBaseVersion = 1;
    private Context  context;


    // favourite Question table
    private static final String tableNameFQ = "Favourite_Question";
    private static final String tableNameFQC1 = "id";
    private static final String tableNameFQC2 = "Tag";
    private static final String tableNameFQC3 = "Question";
    private static final String tableNameFQC4 = "Answer";


    public MainDb(@Nullable Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QueryFavouriteQ = "create TABLE " +  tableNameFQ+ "("+tableNameFQC1
                +" integer primary key autoincrement, "+tableNameFQC2+" text, "
                +tableNameFQC3+" text, "+tableNameFQC4+" text);";
        db.execSQL(QueryFavouriteQ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + tableNameFQ);
        onCreate(db);
    }
    void AddToFav(String tag , String Q , String ans){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(tableNameFQC2,tag);
        contentValues.put(tableNameFQC3,Q);
        contentValues.put(tableNameFQC4,ans);

        long res = db.insert(tableNameFQ, null, contentValues);
        if (res == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Successfully, Added", Toast.LENGTH_SHORT).show();

    }
    void delfromFav(String Q ){
        SQLiteDatabase db = this.getWritableDatabase();

        long res = db.delete(tableNameFQ, "Question ="+Q,null);
        if (res == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Successfully, delete", Toast.LENGTH_SHORT).show();

    }
}
