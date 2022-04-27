package com.example.tensorhw8

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tensorhw8.adapter.ItemsAdapter
import com.example.tensorhw8.databinding.ActivityMainBinding

const val COLUMNS = 2
const val SPACING = 8

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, COLUMNS)

        val gridDividerItemDecoration = GridDividerItemDecoration(SPACING)
        binding.recyclerView.addItemDecoration(gridDividerItemDecoration)

        adapter = ItemsAdapter(viewModel::deleteItem, viewModel::renameItem)
        binding.recyclerView.adapter = adapter

        viewModel.items.observe(this) {
            Log.d("MainActivity", "reloading adapter")
            adapter.reload(it)
        }

        binding.floatingButton.setOnClickListener {
            viewModel.addRandomEmployee()
        }
    }
}