package xyz.manzodev.demohohang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import xyz.manzodev.demohohang.Model.Model;

public class DatabaseHandle extends SQLiteOpenHelper {
    private static String DB_NAME = "People.db";

    public DatabaseHandle(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q1 = "create table people(\n" +
                "id integer primary key,\n" +
                "name text\n" +
                ");";
        String q2="create table relative(\n" +
                "main_id integer ,\n" +
                "id integer ,\n" +
                "name text,\n" +
                "rela integer,\n" +
                "foreign key (main_id) references people(id)\n" +
                ");";

        //todo q2 add column mother_id
        db.execSQL(q1);
        db.execSQL(q2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("drop table if exists people ");
            db.execSQL("drop table if exists relative ");
            onCreate(db);
        }
    }

    public void addPeople(Model model){
        SQLiteDatabase db = getWritableDatabase();
        String q = "insert into people values("+model.getId()+",'"+model.getName()+"');";
        db.execSQL(q);
    }

    public ArrayList<Model> showPeople(){
        ArrayList<Model> models = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "select * from people";
        Cursor c = db.rawQuery(q,null);
        while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            Model model = new Model(id,name);
            models.add(model);
        }
        return models;
    }
    public void addRelative(Model m1,Model m2,int relative){
        //todo if rela = 4 , add wife
        //rela = 2,0 , add son's mother
        SQLiteDatabase db = getWritableDatabase();
        String q = "insert into relative values ("+m1.getId()+","+ m2.getId()+",'"+m2.getName()+"',"+relative+")";
        String q2 = "insert into relative values ("+m2.getId()+","+ m1.getId()+",'"+m1.getName()+"',"+(2-relative)+")";
        db.execSQL(q);
        db.execSQL(q2);

    }

    public ArrayList<Model> getChild(int id,int rela){
        ArrayList<Model> models = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "select * from relative where (main_id = "+id+" and rela = "+rela+");";
        Cursor c = db.rawQuery(q,null);
        while(c.moveToNext()){
            int mId = c.getInt(c.getColumnIndex("id"));
            String mName = c.getString(c.getColumnIndex("name"));
            models.add(new Model(mId,mName));
        }
        return models;
    }

    //todo 1 :  getWife return ArrayList<Model>

}
