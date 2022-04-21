package com.example.tensorhw8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EmployeesAdapter(
    private val deleteAction: (Int) -> Unit
) : RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>() {

    private val employees = mutableListOf<Employee>()

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.photo)
        val fullName: TextView = view.findViewById(R.id.full_name)
        val department: TextView = view.findViewById(R.id.department)
        val deleteButton: ImageView = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_list_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        with(holder) {
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

    override fun getItemCount() = employees.size

    fun reload(data: List<Employee>) {
        employees.clear()
        employees.addAll(data)
        notifyDataSetChanged()
    }
}