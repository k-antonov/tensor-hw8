package com.example.tensorhw8.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tensorhw8.R
import com.example.tensorhw8.model.Department
import com.example.tensorhw8.model.Employee
import com.example.tensorhw8.model.ListItem

const val EMPLOYEE_TYPE = 1
const val DEPARTMENT_TYPE = 2

const val KEY_NAME = "name"

class ItemsAdapter(
    private val deleteAction: (Int) -> Unit,
    private val renameAction: (Int, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TAG: String = ItemsAdapter::class.java.simpleName
        const val ID_RENAME = 0
        const val ID_DELETE = 1
    }

    private var items = listOf<ListItem>()

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.photo)
        var fullName: TextView = view.findViewById(R.id.full_name)
        val department: TextView = view.findViewById(R.id.department)
        val moreButton: ImageView = view.findViewById(R.id.more_button)
    }

    class DepartmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var departmentTitle: TextView = view.findViewById(R.id.department_title)
        val moreButton: ImageView = view.findViewById(R.id.more_button_department)
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
                    val employee = items[position] as Employee
                    fullName.text = employee.name
                    department.text = employee.department.name

                    Glide.with(photo.context)
                        .load(employee.photoUrl)
                        .centerCrop()
                        .into(photo)

                    moreButton.setOnClickListener {
                        PopupMenuListener.listen(it, deleteAction, renameAction, adapterPosition)
                    }
                }
            }
            is DepartmentViewHolder -> {
                with(holder) {
                    departmentTitle.text = (items[position] as Department).name

                    moreButton.setOnClickListener {
                        PopupMenuListener.listen(it, deleteAction, renameAction, adapterPosition)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.d(TAG, "payloads=$payloads")
        if (payloads.isEmpty() || payloads[0] !is Bundle) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val bundle = payloads[0] as Bundle
            when (holder) {
                is EmployeeViewHolder -> holder.fullName.text = bundle.getString(KEY_NAME)
                is DepartmentViewHolder -> holder.departmentTitle.text = bundle.getString(KEY_NAME)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = when (items[position]) {
        is Employee -> EMPLOYEE_TYPE
        is Department -> DEPARTMENT_TYPE
        else -> throw IllegalAccessException("Wrong ItemViewType")
    }

    fun reload(newList: List<ListItem>) {
        val oldList = items
        Log.d(TAG, "$oldList")
        Log.d(TAG, "$newList")
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            ItemsDiffUtilCallback(oldList, newList)
        )
        items = newList
        Log.d(TAG, "diffResult=$diffResult")
        diffResult.dispatchUpdatesTo(this)
    }
}