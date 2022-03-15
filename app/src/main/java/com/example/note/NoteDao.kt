package com.example.note

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    //specifying preDefined Queries wit Fun
    //Also can specify/Handle the conflict condition by -> onConflict
    //suspend is a coroutine    --->this make the function only called through background thread
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend  fun insert(note: Note)

    //Room has Kotlin coroutines support.
    //This allows your queries to be annotated with the suspend modifier and then
    //called from a coroutine or from another suspension function.
    @Delete
    suspend fun delete(note: Note)

    //Custom Query

    //LiveData is a data wrapper which give live access to observer
    @Query("SELECT * from notes order by idd ASC" )
    fun getAllNotes(): LiveData<List<Note>>
}