package com.group3.taskapptoti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TaskListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private var viewModel = MainActivityViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        recyclerView = view.findViewById(R.id.list_item)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.initializeDatabase(requireContext())



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = TaskAdapter(emptyList()) { taskToDelete ->
            viewModel.deleteTask(taskToDelete)
            Toast.makeText(requireContext(), "Task deleted successfully", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            adapter.updateTasks(tasks)
        }

    }
}


