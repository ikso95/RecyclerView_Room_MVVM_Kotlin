package com.foxcode.notes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Dao
import com.foxcode.notes.R
import com.foxcode.notes.adapters.DaoAdapter
import com.foxcode.notes.room.Person
import com.foxcode.notes.viewmodels.PeopleViewModel

class ShowList : AppCompatActivity() {

    private lateinit var viewModel: PeopleViewModel
    private lateinit var daoAdapter: DaoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listOfPeople: LiveData<List<Person>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(PeopleViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listOfPeople = viewModel.getAllPeople()
        listOfPeople.observe(this, Observer {
            if(it.isNotEmpty()){
                daoAdapter = DaoAdapter(it)
                recyclerView.adapter = daoAdapter
            }
        })

    }


}