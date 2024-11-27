package com.example.medimeet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {
    lateinit var homebtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val emailInput = findViewById<EditText>(R.id.editTextEmail)
        val feedbackInput = findViewById<EditText>(R.id.editTextFeedback)
        val submitButton = findViewById<Button>(R.id.buttonSubmit)

        homebtn=findViewById(R.id.homeicon)
        homebtn.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        submitButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val feedback = feedbackInput.text.toString()

            if (name.isEmpty() || email.isEmpty() || feedback.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                // You can handle saving feedback or sending it to the server here
                Toast.makeText(this, "Thank you for your feedback, $name!", Toast.LENGTH_SHORT).show()
                // Clear inputs after submission
                nameInput.text.clear()
                emailInput.text.clear()
                feedbackInput.text.clear()
            }
        }
    }
}
