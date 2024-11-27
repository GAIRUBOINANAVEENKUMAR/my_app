package com.example.medimeet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Waitlist : AppCompatActivity() {
    lateinit var db: FirebaseDatabase
    lateinit var list: ArrayList<StudentModel>
    lateinit var recyclerView: RecyclerView
    lateinit var adapterStudent: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waitlist)

        recyclerView = findViewById(R.id.rec)
        list = ArrayList()
        adapterStudent = StudentAdapter(list)
        recyclerView.adapter = adapterStudent
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Firebase database instance
        db = FirebaseDatabase.getInstance()
        val messagesReference = db.reference.child("gnk")

        // Set up the ValueEventListener
        messagesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Clear the list before adding new data
                list.clear()

                // Retrieve all data and add to the list
                for (snapshot in dataSnapshot.children) {
                    val student = snapshot.getValue(StudentModel::class.java)
                    if (student != null) {
                        list.add(student)
                    }
                }

                // Notify adapter about data changes
                adapterStudent.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database read errors
                Toast.makeText(this@Waitlist, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
