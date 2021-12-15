package com.example.recipeapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Celebrity(val name: String, val taboo1: String, val taboo2: String, val taboo3: String, val pk: Int)

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private val apiInterface by lazy { APIClient().getClient().create(APIInterface::class.java) }

    private lateinit var progressDialog: ProgressDialog

    private lateinit var btAdd: Button
    private lateinit var etCelebrity: EditText
    private lateinit var btDetails: Button

    private lateinit var celebrities: ArrayList<Celebrity>
    private var celebrityID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celebrities = arrayListOf()
        celebrityID = intent.extras!!.getInt("celebrityID", 0)
        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(celebrities)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        btAdd = findViewById(R.id.btAdd)
        btAdd.setOnClickListener {
            add()
            val celebrityNames = arrayListOf<String>()
            for(c in celebrities){
                celebrityNames.add(c.name.lowercase())
            }
            intent.putExtra("celebrityNames", celebrityNames)
            startActivity(intent)
        }
        etCelebrity = findViewById(R.id.etCelebrity)
        btDetails = findViewById(R.id.btDetails)
        btDetails.setOnClickListener {
            if(etCelebrity.text.isNotEmpty()){
                updateCelebrity()
            }else{
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show()
            }
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait")
        progressDialog.show()

        getCelebrities()
    }

    private fun getCelebrities(){
        apiInterface.getCelebrities().enqueue(object: Callback<ArrayList<Celebrity>>{
            override fun onResponse(
                call: Call<ArrayList<Celebrity>>,
                response: Response<ArrayList<Celebrity>>
            ) {
                progressDialog.dismiss()
                celebrities = response.body()!!
                rvAdapter.update(celebrities)
            }

            override fun onFailure(call: Call<ArrayList<Celebrity>>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Unable to get data", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateCelebrity(){
        var celebrityID = 0
        for(celebrity in celebrities){
            if(etCelebrity.text.toString().capitalize() == celebrity.name){
                celebrityID = celebrity.pk
                updat()
            }else{
                Toast.makeText(this, "${etCelebrity.text.toString().capitalize()} not found", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun updat(){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dilog_custom, null)
        val name = dialogLayout.findViewById<EditText>(R.id.name)
        val tabo1 = dialogLayout.findViewById<EditText>(R.id.Tabo1)
        val tabo2= dialogLayout.findViewById<EditText>(R.id.Tabo2)
        val tabo3 = dialogLayout.findViewById<EditText>(R.id.Tabo3)
        builder.setView(dialogLayout)
        builder.setNeutralButton("updat") { dialog, which ->
            Toast.makeText(applicationContext, "updat", Toast.LENGTH_SHORT).show()
            if(name.text.isNotEmpty() && tabo1.text.isNotEmpty()){
                val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
//(val name: String, val taboo1: String, val taboo2: String, val taboo3: String, val pk: Int)
                apiInterface?.addCelebrity(
                    Celebrity(
                        name.text.toString(),
                        tabo1.text.toString(),
                        tabo2.text.toString(),
                        tabo3.text.toString(),
                        0
                    )
                )!!.enqueue(object: Callback<Celebrity> {
                    override fun onResponse(call: Call<Celebrity>, response: Response<Celebrity>) {
                        return
                    }

                    override fun onFailure(call: Call<Celebrity>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    }
                })
            }else{
                Toast.makeText(applicationContext, "Please enter title and author", Toast.LENGTH_LONG).show()
            }
        }
        builder.setNegativeButton("delet") { dialog, which ->
            Toast.makeText(applicationContext, "delet", Toast.LENGTH_SHORT).show()

            apiInterface.deleteCelebrity(celebrityID).enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Celebrity Deleted", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
                }

            })

        }
        builder.setPositiveButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }

    fun add() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dilog_custom, null)
        val name = dialogLayout.findViewById<EditText>(R.id.name)
        val tabo1 = dialogLayout.findViewById<EditText>(R.id.Tabo1)
        val tabo2= dialogLayout.findViewById<EditText>(R.id.Tabo2)
        val tabo3 = dialogLayout.findViewById<EditText>(R.id.Tabo3)
        builder.setView(dialogLayout)
        builder.setNeutralButton("Add") { dialog, which ->
            Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
            if(name.text.isNotEmpty() && tabo1.text.isNotEmpty()){
                val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
//(val name: String, val taboo1: String, val taboo2: String, val taboo3: String, val pk: Int)
                apiInterface?.addCelebrity(
                    Celebrity(
                        name.text.toString(),
                        tabo1.text.toString(),
                        tabo2.text.toString(),
                        tabo3.text.toString(),
                        0
                    )
                )!!.enqueue(object: Callback<Celebrity> {
                    override fun onResponse(call: Call<Celebrity>, response: Response<Celebrity>) {
                        return
                    }

                    override fun onFailure(call: Call<Celebrity>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    }
                })
            }else{
                Toast.makeText(applicationContext, "Please enter title and author", Toast.LENGTH_LONG).show()
            }
        }

        builder.setPositiveButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }
}