package com.group3.taskapptoti

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.group3.taskapptoti.db.AppDatabase
import com.group3.taskapptoti.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel() {

    private lateinit var database: AppDatabase

    private val _tasks = MutableLiveData<List<TaskClass>>()
    val tasks: LiveData<List<TaskClass>> get() = _tasks

    fun initializeDatabase(context: Context){

        database = AppDatabase.getDatabase(context)
        loadTasks()
    }

    fun addTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            database.taskDao().insertTask(task)
            loadTasks()
        }
    }

    private fun loadTasks() {
        CoroutineScope(Dispatchers.IO).launch {
          database.taskDao().getAll().collect{list ->
              val mappedList = list.map {
                  TaskClass(it.id,it.title,it.description)
              }
              _tasks.postValue(mappedList)
          }

        }
    }

    /*aqui estoy trabajando*/
    fun updateTask(task: TaskClass) {
        CoroutineScope(Dispatchers.IO).launch {
            task.id?.let {
                val taskUpdate = Task(
                    id = it,
                    title = task.title,
                    description = task.description
                )
                database.taskDao().update(taskUpdate)
                loadTasks()
            }
        }
    }
    /*aqui estoy trabajando*/





    fun deleteTask(task: TaskClass) {
        CoroutineScope(Dispatchers.IO).launch {
            task.id?.let { id ->

                val taskForDelete = Task(
                    id = id,
                    title = task.title,
                    description = task.description
                )
                database.taskDao().delete(taskForDelete)

                loadTasks()
            }
        }
    }

}