package com.malikazizali.challengechapter4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.malikazizali.challengechapter4.room.DataNote
import com.malikazizali.challengechapter4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteViewModel(app : Application): AndroidViewModel(app) {
    var allNote: MutableLiveData<List<DataNote>>

    init {
        allNote = MutableLiveData()
        getAllNote()
    }

    fun getAllNoteObservers(): MutableLiveData<List<DataNote>> {
        return allNote
    }


    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }

    fun insertNote(entity: DataNote) {
        GlobalScope.async {
            val noteDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            noteDao.insertNote(entity)
            getAllNote()
        }
    }

    fun deleteNote(entity: DataNote) {
        GlobalScope.async {
            val noteDao = NoteDatabase.getInstance(getApplication())?.noteDao()
            noteDao?.deleteNote(entity)
            getAllNote()
        }
    }

    fun updateNote(entity: DataNote) {
        GlobalScope.async {
            val noteDao = NoteDatabase.getInstance(getApplication())?.noteDao()
            noteDao?.updateNote(entity)
            getAllNote()
        }
    }


}