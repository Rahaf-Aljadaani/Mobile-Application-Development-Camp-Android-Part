package ran.am.registration

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomePage : AppCompatActivity() {
    lateinit var dbhr: DatabaseHandler
    lateinit var tv : TextView
    lateinit var tv2 : TextView
    lateinit var spf: SharedPreferences
    lateinit var d: String
    lateinit var s: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        dbhr = DatabaseHandler(applicationContext)
        tv=findViewById(R.id.textView)
        tv2=findViewById(R.id.textView2)
        spf =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        s = spf.getString("mob","default value").toString()

        tv.setText("Welcome "+s+"!"+"\n")

        d = dbhr.getdet(s)

        tv2.setText("Your details are \n"+d+"\n")


    }
}