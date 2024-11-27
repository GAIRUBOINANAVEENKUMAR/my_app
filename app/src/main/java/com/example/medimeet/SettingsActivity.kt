package com.example.medimeet

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medimeet.R

class SettingsActivity : AppCompatActivity() {

    lateinit var homebtn:ImageView
    private lateinit var notificationsSwitch: Switch
    private lateinit var darkModeSwitch: Switch
    private lateinit var soundSwitch: Switch
    private lateinit var changePasswordButton: Button
     private lateinit var clearDataButton: Button
    private lateinit var saveSettingsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize views
        notificationsSwitch = findViewById(R.id.notificationsSwitch)
        darkModeSwitch = findViewById(R.id.darkModeSwitch)
        soundSwitch = findViewById(R.id.soundSwitch)
        changePasswordButton = findViewById(R.id.changePasswordButton)
         clearDataButton = findViewById(R.id.clearDataButton)
        saveSettingsButton = findViewById(R.id.saveSettingsButton)

        homebtn=findViewById(R.id.homeicon)
        homebtn.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        // Set listeners for various actions
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Notifications Enabled" else "Notifications Disabled"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Dark Mode Enabled" else "Dark Mode Disabled"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Sound Enabled" else "Sound Disabled"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        changePasswordButton.setOnClickListener {
            // Logic for changing password, for now, show a toast
            Toast.makeText(this, "Change Password clicked", Toast.LENGTH_SHORT).show()
        }

        clearDataButton.setOnClickListener {
            // Logic to clear app data, for now, show a toast
            Toast.makeText(this, "App Data Cleared", Toast.LENGTH_SHORT).show()
        }

        saveSettingsButton.setOnClickListener {
            // Save settings logic, for now, show a toast

            Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
