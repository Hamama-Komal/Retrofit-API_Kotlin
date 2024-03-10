package com.example.retrofitkotlin.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.adapter.UserAdapater
import com.example.retrofitkotlin.databinding.ActivityMainBinding
import com.example.retrofitkotlin.interfaces.APIInterface
import com.example.retrofitkotlin.model.UsersItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var BASE_URL = "https://api.github.com"

    private lateinit var userAdapater: UserAdapater



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.userRecycler.layoutManager = LinearLayoutManager(this)
        getallData();

    }

    private fun getallData() {

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(APIInterface::class.java)

        var retroData = retrofit.getData()

        retroData.enqueue(object : Callback<List<UsersItem>>{
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                val data = response.body()!!
               // Log.d("data" , data.toString())
                userAdapater = UserAdapater(this@MainActivity,data)
                binding.userRecycler.adapter = userAdapater
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}