package com.example.employeeroomdb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeeroomdb.data.Employee
import com.example.employeeroomdb.data.EmployeeAdapter
import com.example.employeeroomdb.data.EmployeeDatabase
import kotlinx.android.synthetic.main.activity_employee_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager : LinearLayoutManager
    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        val idEmp = intent.getIntExtra("RowId", 0)
        val nameEmp = intent.getStringExtra("RowName").toString()
        val contactEmp = intent.getStringExtra("RowContact").toString()
        val emailEmp = intent.getStringExtra("RowEmail").toString()
        val check = intent.getIntExtra("Check", 0)

        val emp = Employee(nameEmp, contactEmp, emailEmp)
        emp.id = idEmp
        linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager


        CoroutineScope(Main).launch {
            val employee = EmployeeDatabase(this@EmployeeListActivity).getEmployeeDao().getAllEmployee()
            recycler_view.adapter = EmployeeAdapter(employee)
        }
        if (check == 1) {
            CoroutineScope(Main).launch {
                EmployeeDatabase(this@EmployeeListActivity).getEmployeeDao().deleteEmployee(emp)
                Toast.makeText(this@EmployeeListActivity, "Entry Deleted", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}