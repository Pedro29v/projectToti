package com.group3.taskapptoti

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var button: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButton()

    }

    private fun setupButton() {

        button = findViewById(R.id.AddTaskButton)
        button.setOnClickListener{
            handleButtonClick()
        }
    }

    private fun handleButtonClick() {

        val intent = Intent(applicationContext,FormNewTaskActivity::class.java)
        startActivity(intent)
    }


}
