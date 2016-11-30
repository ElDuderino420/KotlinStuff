package safevandinc.kotlindatabase

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import safevandinc.kotlindatabase.data.TaskContract.TaskEntry
import safevandinc.kotlindatabase.data.TaskDbHelper
import safevandinc.kotlindatabase.data.TaskContract

class CreateTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        val saveBtn = findViewById(R.id.save_task_btn) as Button

        saveBtn.setOnClickListener({ view ->
            val taskTitleEditText = findViewById(R.id.task_title) as EditText
            val taskTitle = taskTitleEditText.text.toString()

            val taskDescriptionEditText = findViewById(R.id.task_description) as EditText
            val taskDescription: String = taskDescriptionEditText.text.toString()

            if (taskTitle.isEmpty() or taskDescription.isEmpty()) {
                val inputEmpty = getString(R.string.error_input_empty)

                Toast.makeText(applicationContext, inputEmpty, Toast.LENGTH_LONG).show()
            } else {
                val values = ContentValues()
                values.put(TaskContract.TaskEntry.COL_TITLE, taskTitle)
                values.put(TaskContract.TaskEntry.COL_DESCRIPTION, taskDescription)

                var inserted = contentResolver.insert(TaskContract.TaskEntry.CONTENT_URI, values)

                startActivity(Intent(this, MainActivity::class.java))

                Log.d("New Task", "inserted: $inserted")
            }
        })
    }
}