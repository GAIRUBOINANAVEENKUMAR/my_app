package com.example.medimeet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutUsActivity : AppCompatActivity() {
    lateinit var homebtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        homebtn=findViewById(R.id.homeicon)

        homebtn.setOnClickListener {
            // Navigate to the new activity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        // Instagram Icon Click Event
        val instagramIcon = findViewById<ImageView>(R.id.linkedinIcon)
        instagramIcon.setOnClickListener {
            val url = "https://www.linkedin.com/in/naveen-kumar-gairuboina-754649276/"
            openUrl(url)
        }

        // Facebook Icon Click Event
        val facebookIcon = findViewById<ImageView>(R.id.githubIcon)
        facebookIcon.setOnClickListener {
            val url = "https://github.com/GAIRUBOINANAVEENKUMAR"
            openUrl(url)
        }

        // Gmail Icon Click Event
        val gmailIcon = findViewById<ImageView>(R.id.instaIcon)
        gmailIcon.setOnClickListener {
            val url = "https://www.instagram.com/naveen_gairuboina/"
            openUrl(url)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
