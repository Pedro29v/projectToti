package com.group3.taskapptoti.db

import androidx.room.Dao
import androidx.room.Insert
import com.group3.taskapptoti.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)
}