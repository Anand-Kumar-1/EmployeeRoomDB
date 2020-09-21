package com.example.employeeroomdb

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun createNewEntry(view : View){
        val intent = Intent(this,EntryActivity::class.java)
        startActivity(intent)
    }
    fun fetchEntries(view : View){
        val intentTwo = Intent(this,EmployeeListActivity::class.java)
        startActivity(intentTwo)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}