package com.example.tensorhw8

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tensorhw8.model.Department
import com.example.tensorhw8.model.Employee
import com.example.tensorhw8.model.ListItem

class MainViewModel : ViewModel() {

    private val mutableItems =
        MutableLiveData(Employee.getMockEmployees() + Department.getMockDepartments())
    val items: LiveData<List<ListItem>>
        get() = mutableItems

    fun addRandomEmployee() {
        mutableItems.value = mutableItems.value?.toMutableList()?.apply {
            add(Employee.getMockEmployees().random())
        }
    }

    fun deleteItem(position: Int) {
        mutableItems.value = mutableItems.value?.toMutableList()?.apply {
            Log.d("MainViewModel", "position to remove=$position")
            removeAt(position)
        }
    }

    fun renameItem(position: Int, newName: String) {
        val newItem = when (val item = mutableItems.value?.get(position)) {
            is Employee -> item.copy(name = newName)
            is Department -> item.copy(name = newName)
            else -> throw ClassNotFoundException()
        }

        mutableItems.value = mutableItems.value?.toMutableList()?.apply {
            set(position, newItem)
        }
    }
}