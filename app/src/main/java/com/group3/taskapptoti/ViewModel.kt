package com.group3.taskapptoti

import android.content.Context
import com.group3.taskapptoti.db.AppDatabase
import com.group3.taskapptoti.model.Task

class ViewModel {

    private lateinit var database: AppDatabase

    fun initializeDatabase(context: Context){

        database = AppDatabase.getDatabase(context)
    }

    fun addTask(task: Task){
        database.taskDao().insertTask(task)

    }


}