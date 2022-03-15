package com.example.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    var allNote : LiveData<List<Note>>
    private val repository:NoteRepository
    init{
        //creating NoteDataBase object entry point in Database
        val dao=NoteDataBase.getDatabase(application).getNoteDao()

        //creating repository object work over dao
        repository=NoteRepository(dao)

        //using repository---------
        allNote=repository.allNote
    }


    //As suspend function can't call by a normal foreGround function
    //Make this function Coroutine function to work on backGround thread
    fun deleteNote(note:Note)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun insertNote(note:Note)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

}