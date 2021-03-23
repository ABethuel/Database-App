package com.example.databaseapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.databaseapp.Model.Drivers;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="app_database.db";
    private  static final int DB_VER=1;


    public Database(Context context){
        super(context, DB_NAME, null, DB_VER);
    }

    //Method to get all the drivers
    public List<Drivers> getDrivers()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id", "Year", "Name", "Nationality", "Team"};
        String tableName="Formula1WC";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null,null, null, null );
        List<Drivers> result = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                Drivers drivers = new Drivers();
                drivers.setId(cursor.getInt(cursor.getColumnIndex("id")));
                drivers.setYear(cursor.getString(cursor.getColumnIndex("Year")));
                drivers.setName(cursor.getString(cursor.getColumnIndex("Name")));
                drivers.setNationality(cursor.getString(cursor.getColumnIndex("Nationality")));
                drivers.setTeam(cursor.getString(cursor.getColumnIndex("Team")));

                result.add(drivers);
            }while (cursor.moveToNext());
        }
        return result;
    }

    //Get all names
    public List<String> getNames()
    {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Name"};
        String tableName="Formula1WC";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null,null, null, null );
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Name")));
            }while (cursor.moveToNext());
        }
        return result;
    }

    // Get all drivers by name
    public List<Drivers> getDriverByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id", "Year", "Name", "Nationality", "Team"};
        String tableName="Formula1WC";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "Name LIKE ?", new String[] {"%"+name+"%"},null, null, null );
        List<Drivers> result = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                Drivers driver = new Drivers();
                driver.setId(cursor.getInt(cursor.getColumnIndex("id")));
                driver.setYear(cursor.getString(cursor.getColumnIndex("Year")));
                driver.setName(cursor.getString(cursor.getColumnIndex("Name")));
                driver.setNationality(cursor.getString(cursor.getColumnIndex("Nationality")));
                driver.setTeam(cursor.getString(cursor.getColumnIndex("Team")));

                result.add(driver);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
