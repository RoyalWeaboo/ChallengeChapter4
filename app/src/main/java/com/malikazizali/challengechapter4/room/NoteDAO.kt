package com.malikazizali.challengechapter4.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDAO {
    @Insert
    fun insertNote(note: DataNote)

    @Query("SELECT * FROM DataNote ORDER BY id DESC ")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deleteNote(note: DataNote)

    @Update
    fun updateNote(note: DataNote)

    @Query("SELECT COUNT(*) FROM DataNote")
    fun checkData() : Int

}