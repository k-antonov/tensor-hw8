package com.example.tensorhw8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tensorhw8.databinding.ActivityMainBinding

const val COLUMNS = 2

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: EmployeesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, COLUMNS)
        adapter = EmployeesAdapter(viewModel::deleteEmployee)
        binding.recyclerView.adapter = adapter

        viewModel.employees.observe(this) {
            adapter.reload(it)
        }

        binding.floatingButton.setOnClickListener {
            viewModel.addRandomEmployee()
        }
    }
}