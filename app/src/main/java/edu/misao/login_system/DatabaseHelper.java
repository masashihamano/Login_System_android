package edu.misao.login_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masashihamano on 2018/02/09.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    Context context;
    SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context){
        super(context,"masa",null,1);
        this.context =context;
    }


    @Override
    public void onCreate(SQLiteDatabase SQLdb) {
        SQLdb.execSQL( "create table masa(id Integer PRIMARY KEY AUTOINCREMENT, name text, password varchar(10), email varchar(20), phone integer )" );
        sqLiteDatabase = SQLdb;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        this.onCreate( db );
    }

    //user own method
    public void insertData(String name, String pass, String email, String phone)
    {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues cValues = new ContentValues(  );
            cValues.put( "Name",name );
            cValues.put( "Password",pass );
            cValues.put( "Email",email );
            cValues.put( "Phone",phone );

        sqLiteDatabase.insert( "masa",null,cValues );
    }

    //user own method
    public String login(String email, String pass)
    {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery( "select * from masa where email = '"+email+"'",null );

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            if (cursor.getString( cursor.getColumnIndexOrThrow( "password" ) ).contentEquals( pass ))
            {
                return "OK";
            }
        }
        cursor.close();
        return null;
    }

    public Cursor getAllData()//すべてのデータを表示
    {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery( "select * from masa",null );

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            return cursor;
        }
        cursor.close();
        return null;
    }

    public void deleteAll(){
        sqLiteDatabase = this.getWritableDatabase();//書き換えるためgetReadableではない
       // sqLiteDatabase.delete( "masa",null,null );//こっちでも可
        sqLiteDatabase.execSQL( "delete from masa" );
    }


}
