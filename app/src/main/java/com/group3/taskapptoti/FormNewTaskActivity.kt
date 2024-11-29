package com.group3.taskapptoti

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.group3.taskapptoti.model.Task


class FormNewTaskActivity : AppCompatActivity() {

    private val viewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_new_task)


        initializeDatabase()


        findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener {
            saveTask()
        }
    }

    private fun initializeDatabase() {
        viewModel.initializeDatabase(this)
    }

    private fun saveTask() {

        val title = findViewById<EditText>(R.id.input_text).text.toString()
        val description = findViewById<EditText>(R.id.input_text2).text.toString()


        if (title.isBlank() || description.isBlank()) {
            Snackbar.make(
                findViewById(R.id.parent_layout),
                "Please complete all fields",
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }


        val task = Task(title = title, description = description)


        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.addTask(task)
                runOnUiThread {
                    Snackbar.make(
                        findViewById(R.id.parent_layout),
                        "Task saved",
                        Snackbar.LENGTH_LONG
                    ).show()

                    findViewById<EditText>(R.id.input_text).text.clear()
                    findViewById<EditText>(R.id.input_text2).text.clear()

                    finish()
                }

            } catch (e: Exception) {
                Log.e("FormNewTaskActivity", "Error saving the task", e)
                runOnUiThread {
                    Toast.makeText(this@FormNewTaskActivity, "Error saving the task", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

