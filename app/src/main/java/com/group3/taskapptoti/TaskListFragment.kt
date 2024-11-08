package com.group3.taskapptoti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerView = view.findViewById(R.id.list_item)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = listTask()
        val customAdapter = TaskAdapter(dataset)


        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = customAdapter
    }

    private fun listTask(): List<Task> {
        return listOf(
            Task("Levar crianças na escola", "Às 07:00"),
            Task("Fazer compras", "Às 11:00"),
            Task("Dar aula", "Às 19:00"),
            Task("Jogar Red Dead Redemption 2", "Às 22:00"),
            Task("Dormir", "Às 23:00"),
            Task("Trablhar", "fazer entregas do ifood"),



        )
    }
}



