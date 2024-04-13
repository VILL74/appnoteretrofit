package com.example.parcial_2_jg.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import androidx.lifecycle.Observer
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.parcial_2_jg.R
import com.example.parcial_2_jg.ui.viewModels.NoteViewModels

class NoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModels
    lateinit var inp_title: TextView
    lateinit var inp_content: TextView
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        noteViewModel = ViewModelProvider(this).get(NoteViewModels::class.java)

        inp_title = findViewById(R.id.title_note_final)
        inp_content = findViewById(R.id.contenido_note_final)

        id = intent.getIntExtra("id", 0)

        noteViewModel.note.observe(this, Observer { note ->
            inp_title.text = note.title
            inp_content.text = note.content
        })


        noteViewModel.getNoteById(id)
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getNoteById(id)
    }
}