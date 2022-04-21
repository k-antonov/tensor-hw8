package com.example.tensorhw8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mutableEmployees = MutableLiveData(Employee.getMockEmployees())
    val employees: LiveData<List<Employee>>
        get() = mutableEmployees

    fun addRandomEmployee() {
        mutableEmployees.value = mutableEmployees.value?.toMutableList()?.apply {
            add(Employee.getMockEmployees().random())
        }
    }

    fun deleteEmployee(position: Int) {
        mutableEmployees.value = mutableEmployees.value?.toMutableList()?.apply {
            removeAt(position)
        }
    }
}