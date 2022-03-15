package com.example.note

import androidx.lifecycle.LiveData

class NoteRepository(val noteDao: NoteDao) {

    val allNote : LiveData<List<Note>> =noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

}