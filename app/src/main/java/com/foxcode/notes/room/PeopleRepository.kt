package com.foxcode.notes.room

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class PeopleRepository (application: Application) {

    private var peopleDao: PeopleDAO

    init {
        val database = PeopleDatabase.getInstance(application.applicationContext)
        peopleDao = database!!.peopleDao()
    }

    fun insertPerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
        peopleDao.insert(person)
    }

    fun updatePerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
        peopleDao.update(person)
    }

    fun deletePerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
        peopleDao.delete(person)
    }

    fun getAllPeopleAsync() : Deferred<LiveData<List<Person>>> = CoroutineScope(Dispatchers.IO).async {
        peopleDao.getAllPeople()
    }

    fun deleteAllPeople() = CoroutineScope(Dispatchers.IO).launch {
        peopleDao.deleteAllRows()
    }


}