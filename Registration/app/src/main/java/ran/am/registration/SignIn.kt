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

class SignIn : AppCompatActivity() {
lateinit var db: DatabaseHandler
lateinit var phone: EditText
lateinit var pw: EditText
lateinit var spf: SharedPreferences
lateinit var editr: SharedPreferences.Editor

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_in)

    db = DatabaseHandler(this)

    phone = findViewById(R.id.ed6)
    pw = findViewById(R.id.ed7)

    spf =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    editr = spf.edit()
}

fun logIn(view: View) {
    var pw_database = db.checkpassword(phone.text.toString())
    if (pw_database.equals(pw.text.toString())){
        editr.putString("mob",phone.text.toString()).commit()

        Toast.makeText(applicationContext, "Sign in success!", Toast.LENGTH_SHORT).show();


        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)

    } else{
        Toast.makeText(applicationContext, "Invaild details", Toast.LENGTH_SHORT).show();
    }
}


}