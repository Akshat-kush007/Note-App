package com.example.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), IonClick {
    lateinit var viewModel:NoteViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var inputText : EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText=findViewById(R.id.txtInput_text)
        button=findViewById(R.id.btButton)

    //recyclerView------------------------------------------------------------------------------------------
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)

        val adapter=NoteRVAdapter(this,this)
        recyclerView.adapter=adapter
    //recyclerView------------------------------------------------------------------------------------------


    //ViewModel----------------------------------------------------------------------------------------------
        viewModel=ViewModelProvider( this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        //Using the liveData method
            //Here we got the list as live data---------
        viewModel.allNote.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })
    //ViewModel----------------------------------------------------------------------------------------------

        button.setOnClickListener {
            submit()
        }



    }

    fun submit(){
        val text=inputText.text.toString()
        if(text.isNotEmpty()){
            viewModel.insertNote(Note(text))
            Toast.makeText(this,"${text} is Inserted...",Toast.LENGTH_SHORT).show()
            inputText.text=null
        }else{
            Toast.makeText(this,"Please Enter Note...",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onClickListeer(item: Note) {
        viewModel.deleteNote(item)
            Toast.makeText(this,"${item.text} is Deleted...",Toast.LENGTH_SHORT).show()

    }
}