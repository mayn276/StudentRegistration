
package com.example.studentregistration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregistration.databinding.ActivityMainBinding
import db.Student
import db.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding:ActivityMainBinding
   /* private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button*/

    private lateinit var viewModel: StudentViewModel
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter
    private var isListItemClicked = false

    private lateinit var selectedStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        /*nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        studentRecyclerView = findViewById(R.id.rvStudent)*/

        val dao = StudentDatabase.getInstance(application).studentDao
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)

        mainBinding.apply {
            btnSave.setOnClickListener {
                if (isListItemClicked) {
                    updateStudentData()
                    clearInput()
                } else {
                    saveStudentData()
                    clearInput()
                }
            }

            btnClear.setOnClickListener {
                if (isListItemClicked) {
                    deleteStudentData()
                    clearInput()
                } else {
                    clearInput()
                }
            }

            initRecyclerView()

        }
    }

    private fun saveStudentData() {
        mainBinding.apply {

        viewModel.insertStudent(
            Student(
                0,
                etName.text.toString(),
                etEmail.text.toString()
            )
        )
    }}

    private fun updateStudentData() {
        mainBinding.apply {
        viewModel.updateStudent(
            Student(
                selectedStudent.id,
                etName.text.toString(),
                etEmail.text.toString()
            )
        )
        btnSave.text = "Save"
        btnClear.text = "Clear"
        isListItemClicked = false
    }}

    private fun deleteStudentData() {
        mainBinding.apply {

        viewModel.deleteStudent(
            Student(
                selectedStudent.id,
                etName.text.toString(),
                etEmail.text.toString()
            )
        )
        btnSave.text = "Save"
        btnClear.text = "Clear"
        isListItemClicked = false
    }}

    private fun clearInput() {
        mainBinding.apply {

        etName.setText("")
        etEmail.setText("")
    }}

    private fun initRecyclerView() {

        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: Student ->
            listItemClicked(selectedItem)
        }
        studentRecyclerView.adapter = adapter

        displayStudentsList()
    }

    private fun displayStudentsList() {
        viewModel.students.observe(
            /* owner = */ this,
        )
        /* observer = */
        {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(student: Student) {
//        Toast.makeText(
//            this,
//            "Student name is ${student.name}",
//            Toast.LENGTH_LONG
//        ).show()
        mainBinding.apply {

        selectedStudent = student
        btnSave.text = "Update"
        btnClear.text = "Delete"
        isListItemClicked = true
        etName.setText(selectedStudent.name)
        etEmail.setText(selectedStudent.email)

    }}
}