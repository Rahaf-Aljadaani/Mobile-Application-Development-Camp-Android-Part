package com.example.recipeapp

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


class Recipe(val pk: Int, val title: String, val author: String, val ingredients: String, val instructions: String)

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var btAdd: Button
    private lateinit var recipes: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recipes = listOf()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(recipes)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        btAdd = findViewById(R.id.btAdd)
        btAdd.setOnClickListener {
            add()
        }

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getAll()?.enqueue(object: Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                recipes = response.body()!!
                rvAdapter.update(recipes)
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.d("MAIN", "Unable to get data")
            }
        })

    }


    fun add() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("To Do")
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dilog_custom, null)
        val name = dialogLayout.findViewById<EditText>(R.id.name)
        val author = dialogLayout.findViewById<EditText>(R.id.author)
        val ingredients = dialogLayout.findViewById<EditText>(R.id.ingredients)
        val instraction = dialogLayout.findViewById<EditText>(R.id.instraction)
        builder.setView(dialogLayout)
        builder.setNeutralButton("Add") { dialog, which ->
            Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
            if(name.text.isNotEmpty() && author.text.isNotEmpty()){
                val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

                apiInterface?.addRecipe(
                    Recipe(
                        0,
                        name.text.toString(),
                        author.text.toString(),
                        ingredients.text.toString(),
                        instraction.text.toString()
                    )
                )!!.enqueue(object: Callback<Recipe> {
                    override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<Recipe>, t: Throwable) {
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