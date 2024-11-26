package com.group3.taskapptoti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var dataSet: List<TaskClass>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_task_list, viewGroup, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    fun updateTasks(newDataSet: List<TaskClass>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(task: TaskClass) {
        view.findViewById<TextView>(R.id.title).text = task.title
        view.findViewById<TextView>(R.id.description).text = task.description
    }


}