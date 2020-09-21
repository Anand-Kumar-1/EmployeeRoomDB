package com.example.employeeroomdb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.employeeroomdb.data.Employee
import com.example.employeeroomdb.data.EmployeeDatabase
import kotlinx.android.synthetic.main.activity_entry.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class EntryActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var check : Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        val idEmp = intent.getIntExtra("RowId",0)
        val nameEmp = intent.getStringExtra("RowName").toString()
        val contactEmp = intent.getStringExtra("RowContact").toString()
        val emailEmp = intent.getStringExtra("RowEmail").toString()
        check = intent.getIntExtra("Check",0)

        if(check !=0) {
            employee_name.setText(nameEmp)
            employee_contact.setText(contactEmp)
            employee_email.setText(emailEmp)
        }

        val emp = Employee(nameEmp,contactEmp,emailEmp)
        emp.id = idEmp

        button_done.setOnClickListener {
            val name = employee_name.text.toString().trim()
            val contact = employee_contact.text.toString().trim()
            val email = employee_email.text.toString().trim()

            if(name.isEmpty()){
                employee_name.error = "Name Required"
                employee_name.requestFocus()
                return@setOnClickListener
            }
            if(contact.isEmpty()){
                employee_contact.error = "Contact Required"
                employee_contact.requestFocus()
                return@setOnClickListener
            }
            if(email.isEmpty()){
                employee_email.error = "Email Required"
                employee_email.requestFocus()
                return@setOnClickListener
            }
            CoroutineScope(Main).launch {

                val employee = Employee(name, contact, email)
                if (check == 1){
                    employee.id = emp.id
                    EmployeeDatabase(this@EntryActivity).getEmployeeDao().updateEmployee(employee)
                    Toast.makeText(this@EntryActivity, "Entry updated", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@EntryActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else {

                    EmployeeDatabase(this@EntryActivity).getEmployeeDao().addEntry(employee)
                    Toast.makeText(this@EntryActivity, "Entry created", Toast.LENGTH_SHORT).show()
                }
            }
            finish()
        }
    }
}