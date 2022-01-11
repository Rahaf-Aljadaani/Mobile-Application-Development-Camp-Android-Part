package com.example.postrequestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Info(val name: String,val location: String,val pk: Int)
class InfoPerson : ArrayList<Info>()


class MainActivity : AppCompatActivity() {
  //  lateinit var clRoot: ConstraintLayout
    lateinit var rvMain: RecyclerView
    lateinit var sendName: EditText
    lateinit var sendLocation: EditText
    lateinit var listOfInfo: ArrayList<Info>
    lateinit var Adapter: itemAdapter
    val apiInterface = APIInfo().getInfo()?.create(APIInterface::class.java)
    lateinit var persons: InfoPerson


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        persons = InfoPerson()

        sendName = findViewById(R.id.editText2)
        sendLocation = findViewById(R.id.editText)


        rvMain = findViewById(R.id.recacl)
        Adapter = itemAdapter(persons)
        rvMain.adapter = Adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        val apiInterface = APIInfo().getInfo()?.create(APIInterface::class.java)

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
        apiInterface!!.addInfo(
            Info( sendName.text.toString(),sendLocation.text.toString(), 0)
        ).enqueue(object : Callback<Info> {
            override fun onResponse(call: Call<Info>, response: Response<Info>) {
                Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
                recreate()
            }

            override fun onFailure(call: Call<Info>, t: Throwable) {
                Log.d("MAIN", "Something went wrong!")
            }

        })

    }
/*
    fun updateAndDelet(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("To Do")
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dilog_custom, null)
        val id = dialogLayout.findViewById<EditText>(R.id.Id)
        val name = dialogLayout.findViewById<EditText>(R.id.Name)
        val location = dialogLayout.findViewById<EditText>(R.id.Location)
        builder.setView(dialogLayout)
        builder.setNeutralButton("Update") { dialog, which ->
            Toast.makeText(applicationContext, "Update", Toast.LENGTH_SHORT).show()
            apiInterface?.updateUser(
                Integer.parseInt(id.text.toString()),  // we pass in the user ID
                Info(  // here we pass in the updated information
                    name.text.toString(),
                    location.text.toString(),
                    Integer.parseInt(id.text.toString())
                ))?.enqueue(object: Callback<InfoPerson> {
                override fun onResponse(call: Call<InfoPerson>, response: Response<InfoPerson>) {
                    Toast.makeText(applicationContext, "User Updated", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<InfoPerson>, t: Throwable) {
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            })

        }

        builder.setPositiveButton("Delete") { dialog, which ->
            Toast.makeText(applicationContext, "Delete", Toast.LENGTH_SHORT).show()
            apiInterface?.deleteUser(Integer.parseInt(id.text.toString()))?.enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "User Deleted", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }

        builder.show()
    }
*/
}
