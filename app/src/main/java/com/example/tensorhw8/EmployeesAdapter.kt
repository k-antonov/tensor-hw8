package com.example.tensorhw8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tensorhw8.model.Department
import com.example.tensorhw8.model.Employee
import com.example.tensorhw8.model.ListItem

const val EMPLOYEE_TYPE = 1
const val DEPARTMENT_TYPE = 2

class EmployeesDiffUtilCallback(
    private val oldList: List<ListItem>,
    private val newList: List<ListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldList[oldItemPosition]
        val newEmployee = newList[newItemPosition]
        return oldEmployee.id == newEmployee.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldList[oldItemPosition]
        val newEmployee = newList[newItemPosition]
        return oldEmployee == newEmployee
    }
}

class EmployeesAdapter(
    private val deleteAction: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val employees = mutableListOf<ListItem>()

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.photo)
        val fullName: TextView = view.findViewById(R.id.full_name)
        val department: TextView = view.findViewById(R.id.department)
        val deleteButton: ImageView = view.findViewById(R.id.delete_button)
    }

    class DepartmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val departmentTitle: TextView = view.findViewById(R.id.department_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            EMPLOYEE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.employee_list_item, parent, false)
                EmployeeViewHolder(view)
            }
            DEPARTMENT_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.department_list_item, parent, false)
                DepartmentViewHolder(view)
            }
            else -> throw IllegalAccessException("Wrong ItemViewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmployeeViewHolder -> {
                with(holder) {
                    val employee = employees[position] as Employee
                    fullName.text = employee.fullName
                    department.text = employee.department.title

                    Glide.with(photo.context)
                        .load(employee.photoUrl)
                        .centerCrop()
                        .into(photo)

                    deleteButton.setOnClickListener {
                        deleteAction(position)
                    }
                }
            }
            is DepartmentViewHolder -> {
                holder.departmentTitle.text = (employees[position] as Department).title
            }
        }
    }

    override fun getItemCount() = employees.size

    override fun getItemViewType(position: Int) = when (employees[position]) {
        is Employee -> EMPLOYEE_TYPE
        is Department -> DEPARTMENT_TYPE
        else -> throw IllegalAccessException("Wrong ItemViewType")
    }

    fun reload(data: List<ListItem>) {
        val diffUtilCallback = EmployeesDiffUtilCallback(employees, data)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        employees.clear()
        employees.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}