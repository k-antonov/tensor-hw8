package com.example.tensorhw8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tensorhw8.model.Department
import com.example.tensorhw8.model.Employee
import com.example.tensorhw8.model.ListItem

class MainViewModel : ViewModel() {

    private val mutableEmployees =
        MutableLiveData(Employee.getMockEmployees() + Department.getMockDepartments())
    val employees: LiveData<List<ListItem>>
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