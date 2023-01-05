package com.example.civ2k77

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN + " INTEGER PRIMARY KEY, " +
                LOGIN_COLUMN + " TEXT," +
                PASSWORD_COLUMN + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addUser(login : String, password : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(LOGIN_COLUMN, login)
        values.put(PASSWORD_COLUMN, password)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        //db.close()
    }

    // below method is to get
    // specified data from our database
    fun getUser(login : String, password : String): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        //Log.e("INFO SQLITE", "SELECT * FROM $TABLE_NAME WHERE $LOGIN_COLUMN = $loginGet AND $PASSWORD_COLUMN = $passwordGet")
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $LOGIN_COLUMN = '$login' AND $PASSWORD_COLUMN = '$password'", null)

    }
    fun checkIfLoginExists(login: String) : Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $LOGIN_COLUMN = '$login'", null)//true if exists
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private const val DATABASE_NAME = "Civ2077"

        // below is the variable for database version
        private const val DATABASE_VERSION = 1

        // below is the variable for table name
        const val TABLE_NAME = "Users"

        // below is the variable for id column
        const val ID_COLUMN = "id"

        // below is the variable for login column
        const val LOGIN_COLUMN = "login"

        // below is the variable for password column
        const val PASSWORD_COLUMN = "password"
    }
}