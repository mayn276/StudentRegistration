package com.example.studentregistration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import db.StudentDao

class StudentViewModelFactory(
    private val dao:StudentDao
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}