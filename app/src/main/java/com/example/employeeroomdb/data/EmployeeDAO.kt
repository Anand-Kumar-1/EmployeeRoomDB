package com.example.employeeroomdb.data

import androidx.room.*

@Dao
interface EmployeeDAO {
    @Insert
    suspend fun addEntry(employee : Employee)

    @Query("SELECT * FROM employee")
    suspend fun getAllEmployee() : List<Employee>

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)
}