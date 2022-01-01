package ran.am.registration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SignUp : AppCompatActivity() {
    lateinit var db: DatabaseHandler
    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var loc: EditText
    lateinit var pw: EditText
    lateinit var spf: SharedPreferences
    lateinit var editr: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        db = DatabaseHandler(this)
        name = findViewById(R.id.ed1)
        phone = findViewById(R.id.ed2)
        loc = findViewById(R.id.ed3)
        pw = findViewById(R.id.ed4)
        spf =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editr = spf.edit()
    }

    fun addUser(view: View) {
        var check = db.saveUser(UserModel( name.text.toString(), loc.text.toString(), phone.text.toString(), pw.text.toString()))

        if (check.equals("-1")){
            Toast.makeText(applicationContext, "Error data not saved!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_LONG).show();
            editr.putString("mob",phone.text.toString()).commit()
            val intent = Intent(this, HomePage
            ::class.java)
            startActivity(intent)
        }

    }
}