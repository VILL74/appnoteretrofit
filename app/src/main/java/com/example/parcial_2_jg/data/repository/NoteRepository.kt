package com.example.parcial_2_jg.data.repository

import android.content.Context
//import com.example.parcial_2_jg.data.db.AppDataBase
import com.example.parcial_2_jg.data.model.NoteModel
import com.example.parcial_2_jg.data.remote.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class NoteRepository (context: Context) {

    private val apiClient=ApiClient().getApiService()
  //  private val database=AppDataBase.getInstance(context)

    suspend fun getNotes(): Call<List<NoteModel>> {
        return withContext(Dispatchers.IO){
            apiClient.getNotes()
        }
    }

    suspend fun getNoteId(id: Int): Call<NoteModel> {
        return withContext(Dispatchers.IO) {
            apiClient.getNoteId(id)
        }
    }
}