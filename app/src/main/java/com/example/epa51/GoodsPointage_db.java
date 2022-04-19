package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GoodsPointage_db extends SQLiteOpenHelper {
    public static final String GOODS_POINTAGE_TABLE= "GOODS_POINTAGE_TABLE";
    public static final String COL_ID= "ID";
    public static final String COL_ID_POINTAGE= "ID_POINTAGE";
    public static final String COL_REFERENCE= "REFERENCE";
    public static final String COL_QUANTITY= "QUANTITY";
    public static final String COL_WEIGHT= "WEIGHT";





    public GoodsPointage_db(@Nullable Context context) {
        super(context, "GoodsPointage.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + GOODS_POINTAGE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_POINTAGE + " INTEGER, " + COL_REFERENCE + " TEXT, "+COL_QUANTITY+" INTEGER, "+COL_WEIGHT+" INTEGER)"; //statement to create tables in sql
        sqLiteDatabase.execSQL(tableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean AddGood(Goods_Model good, int IdPintage)
    {

        SQLiteDatabase db=this.getWritableDatabase(); //getWritableDatabase is for writing inside the database
        ContentValues info_holder=new ContentValues();  //ContentValues is like hashmap store data in pairs


        //no need to add ID it is autoincrement

        info_holder.put(COL_ID_POINTAGE,IdPintage);
        info_holder.put(COL_REFERENCE,good.getReference());
        info_holder.put(COL_QUANTITY,good.getQuantite());
        info_holder.put(COL_WEIGHT,good.getPoid());




        long insert = db.insert(GOODS_POINTAGE_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }


    public List<Goods_Model> getGood(int IdPointage)
    {

        List<Goods_Model> returnedList=new ArrayList<>();

        String query="SELECT * FROM "+GOODS_POINTAGE_TABLE+" WHERE "+COL_ID_POINTAGE+" = "+IdPointage;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {

            do {




                String reference=cursor.getString(2);
                int quantite=cursor.getInt(3);
                int weight =cursor.getInt(4);

                Goods_Model newGood=new Goods_Model(reference,quantite,weight);



                returnedList.add(newGood);

            }
            while (cursor.moveToNext());
        }
        else
        {
            //this is a failure so we do nothin
        }



        return returnedList;
    }

}
