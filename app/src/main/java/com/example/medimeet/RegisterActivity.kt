package com.example.medimeet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    lateinit var googleIcon: ImageView
    lateinit var fbIcon: ImageView
    lateinit var twitterIcon: ImageView
    lateinit var homebtn: ImageView
    lateinit var username: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var repassword: EditText
    lateinit var btnRegister: MaterialButton
    lateinit var lgntxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("AppUsers")

        // Initialize the views
        lgntxt = findViewById(R.id.logintxt)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        btnRegister = findViewById(R.id.signupbtn)
        googleIcon = findViewById(R.id.google)
        fbIcon = findViewById(R.id.fb)
        twitterIcon = findViewById(R.id.twitter)
        homebtn = findViewById(R.id.homeicon)

        btnRegister.setOnClickListener {
            val userNameStr = username.text.toString().trim()
            val emailStr = email.text.toString().trim()
            val passwordStr = password.text.toString().trim()
            val repassStr = repassword.text.toString().trim()

            // Validate fields
            when {
                userNameStr.isEmpty() -> {
                    username.error = "Please enter a username"
                }
                emailStr.isEmpty() -> {
                    email.error = "Please enter an email"
                }
                passwordStr.isEmpty() -> {
                    password.error = "Please set password"
                }
                repassStr.isEmpty() -> {
                    repassword.error = "Please re-enter the password"
                }
                passwordStr != repassStr -> {
                    repassword.error = "Passwords do not match"
                }
                else -> {
                    // Check if email is already registered
                    auth.fetchSignInMethodsForEmail(emailStr).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val signInMethods = task.result?.signInMethods
                            if (signInMethods.isNullOrEmpty()) {
                                // Email is not used, proceed with registration
                                auth.createUserWithEmailAndPassword(emailStr, passwordStr)
                                    .addOnCompleteListener { createTask ->
                                        if (createTask.isSuccessful) {
                                            val userId = auth.currentUser?.uid
                                            val user = mapOf(
                                                "username" to userNameStr,
                                                "email" to emailStr,
                                                "password" to passwordStr  // Storing the password (optional, usually not stored like this for security reasons)
                                            )

                                            userId?.let {
                                                database.child(it).setValue(user).addOnCompleteListener {
                                                    Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show()
                                                    startActivity(Intent(this, LoginActivity::class.java))
                                                    finish()
                                                }
                                            }
                                        } else {
                                            Toast.makeText(this, "Registration Failed: ${createTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } else {
                                // Email is already in use
                                Toast.makeText(this, "This email is already registered. Please log in or use another email.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Error checking email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        // Set up onClickListeners for social icons and navigation
        lgntxt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        homebtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        googleIcon.setOnClickListener {
            openUrl("https://www.linkedin.com/in/naveen-kumar-gairuboina-754649276/")
        }

        fbIcon.setOnClickListener {
            openUrl("https://www.linkedin.com/in/naveen-kumar-gairuboina-754649276/")
        }

        twitterIcon.setOnClickListener {
            openUrl("https://www.linkedin.com/in/naveen-kumar-gairuboina-754649276/")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
