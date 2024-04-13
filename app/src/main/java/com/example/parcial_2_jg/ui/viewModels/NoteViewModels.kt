package com.example.parcial_2_jg.ui.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial_2_jg.data.model.NoteModel
import com.example.parcial_2_jg.data.repository.NoteRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class NoteViewModels(application: Application):AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application)

    private val _noteList = MutableLiveData<List<NoteModel>>()
    val noteList: MutableLiveData<List<NoteModel>>
        get() = _noteList

    private val _note = MutableLiveData<NoteModel>()
    val note: MutableLiveData<NoteModel>
        get() = _note

    fun getNotes() {
        viewModelScope.launch {
            val notes = noteRepository.getNotes().enqueue(object : Callback<List<NoteModel>> {

                override fun onResponse(call: Call<List<NoteModel>>,response: Response<List<NoteModel>>) {
                    if (response.isSuccessful) {
                        _noteList.value = response.body()
                    } else{
                        Log.e("NoteViewModel","Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<NoteModel>>, t: Throwable) {
                    Log.e("NoteViewModel","Error: ${t.message}")
                }
            })

        }
    }




    fun getNoteById(id: Int) {
        viewModelScope.launch {
            val noteResponse = noteRepository.getNoteId(id)
            noteResponse.enqueue(object : Callback<NoteModel> {
                override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                    if (response.isSuccessful) {
                        _note.value = response.body()
                    } else {
                        Log.e("NoteViewModel", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                    Log.e("NoteViewModel", "Error: ${t.message}")
                }
            })
        }
    }
}
        /*
    fun updateNote(nid:Long, title:String, description:String){
        viewModelScope.launch {
            val note = NoteModel(nid, title, description)
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note:NoteModel){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            getNotes()
        }
    }

    fun createNote(title:String, description:String){
        viewModelScope.launch {
            val note = NoteModel(null, title, description)
            noteRepository.insertNote(note)
        }
    }*/

