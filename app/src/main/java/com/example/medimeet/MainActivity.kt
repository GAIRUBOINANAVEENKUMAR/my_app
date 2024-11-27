package com.example.medimeet

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
 import android.widget.ImageView
import android.widget.LinearLayout
import android.content.SharedPreferences
import android.net.Uri
import android.view.ContextThemeWrapper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.example.medimeet.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var picon:ImageView
    lateinit var videoCallButton:Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoCallButton = findViewById(R.id.videoCallButton)
        videoCallButton.setOnClickListener {
            val phoneNumber = "+916305439173" // Replace with the doctor's WhatsApp number

            val whatsappIntent = Intent(Intent.ACTION_VIEW)
            whatsappIntent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")

            try {
                startActivity(whatsappIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
            }
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("AppUsers")

        //welcome username
        val username = intent.getStringExtra("username")
        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)

        // If username is available, greet with "Hello, username"
        if (username != null && username.isNotEmpty()) {
            greetingTextView.text = "Hello, $username"
        } else {
            // Fallback if username is null or empty
            greetingTextView.text = "Hello"
        }

        picon=findViewById(R.id.profileIconMain)
        picon.setOnClickListener{
            val intent=Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }



        // Popup Menu
        val optionsMenu: ImageView = findViewById(R.id.optionsMenu)
        optionsMenu.setOnClickListener { view ->
            showPopupMenu(view)
        }
        val marqueeTextView = findViewById<TextView>(R.id.marqueeText)
        marqueeTextView.isSelected = true  // This starts the marquee effect



        // Set up ImageView and Button click listeners
//        val image1: ImageView = findViewById(R.id.image1)
//        val image2: ImageView = findViewById(R.id.image2)
//        val image3: ImageView = findViewById(R.id.image3)
//        val image4: ImageView = findViewById(R.id.image4)

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5:Button=findViewById(R.id.btn_insurence)
        button5.setOnClickListener{
            val i=Intent(this,InsuranceActivity::class.java)
            startActivity(i)
        }

        button1.setOnClickListener { navigateTo(AppointmentActivity::class.java) }
        button2.setOnClickListener { navigateTo(Waitlist::class.java) }
        button3.setOnClickListener { navigateTo( DoctorsActivity::class.java) }
        button4.setOnClickListener { navigateTo(BuyMedicineActivity::class.java) }
    }

    private fun showPopupMenu(view: View) {
        // Apply the custom style to the popup menu
        val popupMenu = PopupMenu(ContextThemeWrapper(this, R.style.CustomPopupMenu), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            handleMenuItemClick(item)
            true
        }
        popupMenu.show()
    }

    private fun handleMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.register -> navigateTo(RegisterActivity::class.java)
            R.id.login -> navigateTo(LoginActivity::class.java)
            R.id.selectLanguage -> navigateTo(LanguageSelectionActivity::class.java)
            R.id.settings -> navigateTo( SettingsActivity::class.java)
            R.id.aboutUs -> navigateTo( AboutUsActivity::class.java)
            R.id.feedback -> navigateTo(FeedbackActivity::class.java)

                R.id.delete -> {
                    deleteAccount()
                    return true
                }
            R.id.logout -> {
                logout()
                return true
            }

            else -> Toast.makeText(this, "Unknown option", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun deleteAccount() {
        val user = auth.currentUser
        if (user != null) {
            // Delete user from Firebase Authentication
            user.delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Also delete from Realtime Database
                    database.child(user.uid).removeValue().addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            Toast.makeText(this, "Account deleted successfully.", Toast.LENGTH_SHORT).show()
                            // Navigate to LoginActivity
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Failed to delete user from database: ${dbTask.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Failed to delete user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun logout() {
        auth.signOut()
        Toast.makeText(this, "Logged out successfully.", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateTo(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
