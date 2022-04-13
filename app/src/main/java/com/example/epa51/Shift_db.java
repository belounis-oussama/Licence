package com.example.epa51;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Shift_db extends SQLiteOpenHelper {




    public static final String SHIFT_TABLE= "Shift_table";
    public static final String COL_ID="ID";
    public static final String COL_NOM_SHIFT = "Nom_shift";
    public static final String COL_DEBUT = "Debut";
    public static final String COL_FIN = "Fin";





    public Shift_db(@Nullable Context context) {
        super(context, "Shifts.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableStatement= "CREATE TABLE " + SHIFT_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM_SHIFT + " TEXT, " + COL_DEBUT + " TEXT,"+ COL_FIN+" TEXT)";
        sqLiteDatabase.execSQL(tableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public boolean addShift(Shift_Modele shift_modele)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues info_holder=new ContentValues();


        info_holder.put(COL_NOM_SHIFT,shift_modele.getNom_shift());
        info_holder.put(COL_DEBUT,shift_modele.getDebut());
        info_holder.put(COL_FIN,shift_modele.getFin());


        long insert = db.insert(SHIFT_TABLE, null, info_holder);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }




    public List<Shift_Modele> getAllShift()
    {
        List<Shift_Modele> returnedList=new ArrayList<>();
        String query="SELECT * FROM "+SHIFT_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {

            do {

                int ShiftId=cursor.getInt(0);
                String Nom_shift=cursor.getString(1);
                String DebutShift=cursor.getString(2);
                String FinShift=cursor.getString(3);


                Shift_Modele shift=new Shift_Modele(ShiftId,Nom_shift,DebutShift,FinShift);

                returnedList.add(shift);

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
