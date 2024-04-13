package com.example.parcial_2_jg.data.remote

import com.example.parcial_2_jg.data.model.NoteModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("note")
    fun getNotes(): Call<List<NoteModel>>

    @GET("note/{id}")
    fun getNoteId(@Path("id") id: Int): Call<NoteModel>
}