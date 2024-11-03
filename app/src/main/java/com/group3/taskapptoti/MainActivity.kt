package com.group3.taskapptoti

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            adapter = TaskAdapter { task ->
                mTaskViewModel.deleteTask(task)

        }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
            mTaskViewModel.readAllData.observe(this, Observer { task ->
                adapter.setData(task)
    }
}
/*
aqui agregue lineas de codigo  a MainActivity para configurar el RecyclerView y la clase TaskAdapter

  */