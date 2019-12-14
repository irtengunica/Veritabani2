package com.example.sbaran.veritabani;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.LongBuffer;
import java.util.ArrayList;

/**
 * Created by sbaran on 10.9.2015.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="aksaray.db";
    public static final int DB_VERSION=1;
    public static final String TBL_AD="arabalar";
    public static final String _ID="_id";
    public static final String MARKA="marka";
    public static final String KATEGORI="kategori";
    public static final String RESIM="resim";
    private  Context ctx;
    private static MyDbHelper dbOrnegi;
    SQLiteDatabase vt;

    public static MyDbHelper VtOrnekGetir(Context ctx)
    {
        if (dbOrnegi==null)
            dbOrnegi = new MyDbHelper(ctx);

        return dbOrnegi;

    }

    public MyDbHelper(Context context) {
        super(context,DATABASE_NAME, null, DB_VERSION);
        this.ctx=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table "+TBL_AD+ " ( "+_ID+ " integer primary key autoincrement, "+
                MARKA +" text, "+ KATEGORI +"  text , "+ RESIM +" integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // mevcut yedek alınır
        db.execSQL("Drop Table If exists "+TBL_AD + ";");
        onCreate(db);
        // yedekten veriler tekrar yükler.

    }

      public long KayitEkle(String marka, String kategori, int resim)
    {
        long id;
        vt=getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(MARKA,marka);
        cv.put(KATEGORI,kategori);
        cv.put(RESIM,resim);

        id=  vt.insert(TBL_AD,null,cv);
        //  vt.execSQL("insert into ");

        vt.close();
        return id;
    }

    public long KayitEkle(ContentValues cv)
    {
        long id;
        vt=getWritableDatabase();
               id=  vt.insert(TBL_AD,null,cv);
        //  vt.execSQL("insert into ");
        vt.close();
        return id;
    }

    public ArrayList<String> AraclariGetir()
    {
        ArrayList<String> lstArac=null;
        vt=getReadableDatabase();
        Cursor cr;
        String sql="Select * from "+ TBL_AD;
        cr=vt.rawQuery(sql,null);
        if (cr!=null)
        {   lstArac = new ArrayList<String>();
            while (cr.moveToNext())
            {
                lstArac.add(cr.getString(cr.getColumnIndex(MARKA)).toString());
            }
        }
        return lstArac;
    }


}
