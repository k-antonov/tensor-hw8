package com.example.tensorhw8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val EMPLOYEE_TYPE = 1
const val DEPARTMENT_TYPE = 2

class EmployeesAdapter(
    private val deleteAction: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val employees = mutableListOf<Any>()

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
            // todo fix DRY violation
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
                    department.text = employee.department

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

    fun reload(data: List<Any>) {
        employees.clear()
        employees.addAll(data)
        notifyDataSetChanged()
    }
}