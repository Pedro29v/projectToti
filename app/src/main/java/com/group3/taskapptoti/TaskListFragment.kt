package com.group3.taskapptoti

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


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


        adapter = TaskAdapter(emptyList(), { taskToDelete ->
            showDeleteConfirmationDialog(taskToDelete)
        },{taskToEdit ->
            val intent = Intent(requireContext(), FormNewTaskActivity::class.java)
            intent.putExtra("task_id", taskToEdit.id)
            intent.putExtra("task_title", taskToEdit.title)
            intent.putExtra("task_description", taskToEdit.description)
            startActivity(intent)
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            adapter.updateTasks(tasks)
        }

    }

    private fun showDeleteConfirmationDialog(task: TaskClass) {
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Delete")
        builder.setMessage("Are you sure you want to delete the task \"${task.title}\"?")

        builder.setPositiveButton("Delete") { _, _ ->
            viewModel.deleteTask(task)
            Snackbar.make(requireView(), "Task eliminated", Snackbar.LENGTH_LONG).show()

        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}


