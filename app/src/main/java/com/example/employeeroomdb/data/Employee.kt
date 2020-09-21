package com.example.employeeroomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity
data class Employee(var name: String, var contact: String, var email: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}