package ran.am.registration

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, "reg.db", null, 1) {

    var sqLiteDatabase: SQLiteDatabase= writableDatabase


    override fun onCreate(db: SQLiteDatabase?) {

        if (db != null) {
            db.execSQL("create table students (Name text , Location text, Mobile text, Password text)")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun saveUser(user:UserModel): Long {
        sqLiteDatabase=writableDatabase

        val cv = ContentValues()
        cv.put("Name", user.nameTx)
        cv.put("Location", user.locTx)
        cv.put("Mobile", user.phoneTx)
        cv.put("Password", user.pwTx)
        return sqLiteDatabase.insert( "students", null, cv)

    }

    fun checkpassword(mobile: String): String {
        var c: Cursor = sqLiteDatabase.query(
            "students", null, "Mobile=?", arrayOf(mobile), null, null, null
        )
        if (c.count < 1) {
            return "name not exists"
        }
        c.moveToFirst()
        var loc = c.getString(c.getColumnIndex("Password"))
        return loc
    }
    fun getdet(mobile: String?): String {
        var c: Cursor = sqLiteDatabase.query(
            "students", null, "Mobile=?", arrayOf(mobile), null, null, null
        )
        if (c.count < 1) {
            return "name not exists"
        }
        c.moveToFirst()
        var loc = c.getString(c.getColumnIndex("Name"))+"\n"+ c.getString(c.getColumnIndex("Location"))
        return loc
    }



}