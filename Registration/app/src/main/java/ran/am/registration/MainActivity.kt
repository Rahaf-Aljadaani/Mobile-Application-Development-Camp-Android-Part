package ran.am.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class UserModel( val nameTx: String, val phoneTx: String, val locTx: String, val pwTx: String)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun SingIn(view: android.view.View) {
        Intent(this, SignIn::class.java).apply {
            startActivity(this)
        }
    }

    fun SingUp(view: android.view.View) {
        Intent(this, SignUp::class.java).apply {
            startActivity(this)
        }
    }
}