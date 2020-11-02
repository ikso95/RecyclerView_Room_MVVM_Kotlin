package com.foxcode.notes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.foxcode.notes.R
import com.foxcode.notes.room.Person
import com.foxcode.notes.viewmodels.PeopleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PeopleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(PeopleViewModel::class.java)

        button_dodaj.setOnClickListener {
            val imie = editText_imie.text.toString()
            val nazwisko = editText_nazwisko.text.toString()
            val pesel = editText_PESEL.text.toString()

            val person = Person(imie, nazwisko, pesel)
            viewModel.insertPerson(person)

            editText_imie.setText("")
            editText_nazwisko.setText("")
            editText_PESEL.setText("")

        }

        button_pokaz.setOnClickListener {
            val intent = Intent(applicationContext,ShowList::class.java)
            startActivity(intent)
        }

        button_usun.setOnClickListener {
            viewModel.deleteAllRows()
        }


    }
}