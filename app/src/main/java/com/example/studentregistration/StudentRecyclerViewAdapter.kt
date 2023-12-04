package com.example.studentregistration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregistration.databinding.ListItemsBinding
import db.Student

class StudentRecyclerViewAdapter(
    private val clickListener:(Student)->Unit
):RecyclerView.Adapter<StudentViewHolder>()  {

    private val studentList = ArrayList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {

        val mainBinding = ListItemsBinding.inflate( LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(mainBinding )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setList(students:List<Student>){
        studentList.clear()
        studentList.addAll(students)
    }

}


class StudentViewHolder(private val mainBinding:ListItemsBinding):RecyclerView.ViewHolder(mainBinding.root){
    fun bind(student: Student, clickListener:(Student)->Unit){
        mainBinding.apply {
        /*val nameTextView = view.findViewById<TextView>(R.id.tvname)
        val emailTextView = view.findViewById<TextView>(R.id.tvemail)*/
        tvname.text = student.name
        tvemail.text = student.email
        root.setOnClickListener {
            clickListener(student)
        }}
    }
}