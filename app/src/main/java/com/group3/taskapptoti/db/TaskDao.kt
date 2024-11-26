package com.group3.taskapptoti.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.group3.taskapptoti.model.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getAll(): Flow<List<Task>>

}