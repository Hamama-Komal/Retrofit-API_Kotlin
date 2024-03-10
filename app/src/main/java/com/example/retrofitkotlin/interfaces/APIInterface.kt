package com.example.retrofitkotlin.interfaces

import com.example.retrofitkotlin.model.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/users")
    fun getData() : Call<List<UsersItem>>
}