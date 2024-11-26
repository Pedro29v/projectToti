package com.group3.taskapptoti

import java.io.Serializable

data class TaskClass(
    val id: Int? = null,
    val title: String,
    val description: String
) : Serializable