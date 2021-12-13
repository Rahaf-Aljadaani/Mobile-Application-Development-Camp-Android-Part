package com.example.playwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Info(val name: String, val location: String,val pk: Int)

class MainActivity : AppCompatActivity() {
    lateinit var clRoot: ConstraintLayout
    lateinit var rvMain: RecyclerView
    lateinit var sendName: EditText
    lateinit var sendLocation: EditText
    lateinit var listOfInfo: ArrayList<Info>
    lateinit var Adapter: itemAdapter
 //   lateinit var but: Button
    val apiInterface = APIInfo().getInfo()?.create(APIInterface::class.java)
    lateinit var persons: InfoPerson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        persons = InfoPerson()
        sendName = findViewById(R.id.editText)
        sendLocation = findViewById(R.id.editText2)
        listOfInfo = ArrayList()
        clRoot = findViewById(R.id.clRoot)
        rvMain = findViewById(R.id.recacl)
        Adapter = itemAdapter(persons)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = Adapter

    //    but = findViewById(R.id.button)

        apiInterface?.getAllInfo()?.enqueue(object: Callback<InfoPerson> {
            override fun onResponse(call: Call<InfoPerson>, response: Response<InfoPerson>) {
                persons = response.body()!!
                Adapter.update(persons)
            }

            override fun onFailure(call: Call<InfoPerson>, t: Throwable) {
                Log.d("MAIN", "Unable to get data")
            }
        })



    }

    fun addInfo(view: View) {

        apiInterface!!.addInfo(Info(sendName.text.toString(), sendLocation.text.toString() , 0)
        ).enqueue(object : Callback<Info> {
            override fun onResponse(call: Call<Info>, response: Response<Info>) {
                Toast.makeText(applicationContext, "Info added!", Toast.LENGTH_LONG).show()
                recreate()
            }

            override fun onFailure(call: Call<Info>, t: Throwable) {
                Log.d("MainActivity", "Error =( !")
            }
        })


    }
}