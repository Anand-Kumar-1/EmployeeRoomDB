package com.example.employeeroomdb.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeroomdb.EmployeeListActivity
import com.example.employeeroomdb.EntryActivity
import com.example.employeeroomdb.R
import kotlinx.android.synthetic.main.employee_layout.view.*

class EmployeeAdapter(private val employee: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder (val view : View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.employee_layout,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return employee.size
    }
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.view.name_recycler_item.text = employee[position].name
        holder.view.contact_recycler_item.text = employee[position].contact
        holder.view.email_recycler_item.text = employee[position].email

        holder.view.setOnClickListener {

            val context = it.context
            val intent = Intent(context, EntryActivity::class.java)
            intent.putExtra("RowId",employee[position].id)
            intent.putExtra("RowName",employee[position].name)
            intent.putExtra("RowContact",employee[position].contact)
            intent.putExtra("RowEmail",employee[position].email)
            intent.putExtra("Check",1)
            context.startActivity(intent)
        }
        holder.view.delete_button.setOnClickListener {
            val intent = Intent(it.context,EmployeeListActivity::class.java)
            intent.putExtra("RowId",employee[position].id)
            intent.putExtra("RowName",employee[position].name)
            intent.putExtra("RowContact",employee[position].contact)
            intent.putExtra("RowEmail",employee[position].email)
            intent.putExtra("Check",1)
            it.context.startActivity(intent)
        }
    }
}