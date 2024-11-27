package com.example.medimeet

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Get references to the views
        val logoImageView: ImageView = findViewById(R.id.appLogo)
        val bookAppointmentText: TextView = findViewById(R.id.bookAppointmentText)
        val getStartedButton: Button = findViewById(R.id.getStartedButton)

        // Load the zoom-in animation from the res/anim/zoom_in.xml file
        val zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        // Apply the zoom-in animation to the app logo and the text
        logoImageView.startAnimation(zoomInAnimation)
        bookAppointmentText.startAnimation(zoomInAnimation)

        // Set a click listener for the "Get Started" button to navigate to the next activity
        getStartedButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity to remove it from the back stack
        }
    }
}
