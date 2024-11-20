package com.group3.taskapptoti.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "Untitled",
    val description: String = "No description"
)
