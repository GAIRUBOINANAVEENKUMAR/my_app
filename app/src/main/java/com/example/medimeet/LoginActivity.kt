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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var lgntxt: TextView
    lateinit var homebtn: ImageView

    lateinit var auth: FirebaseAuth
    lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("AppUsers") // Make sure this matches your database reference

        // Initialize views
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<MaterialButton>(R.id.loginbtn)
        val googleIcon = findViewById<ImageView>(R.id.google)
        val fbIcon = findViewById<ImageView>(R.id.fb)
        val twitterIcon = findViewById<ImageView>(R.id.twitter)

        btnLogin.setOnClickListener {
            val emailStr = email.text.toString().trim()
            val passwordStr = password.text.toString().trim()

            if (emailStr.isNotEmpty() && passwordStr.isNotEmpty()) {
                loginUser(emailStr, passwordStr)
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        lgntxt = findViewById(R.id.registertxt)
        lgntxt.setOnClickListener {
            // Navigate to RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        homebtn = findViewById(R.id.homeicon)
        homebtn.setOnClickListener {
            // Navigate to MainActivity
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

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        // Fetch username from Firebase
                        db.child(userId).get().addOnSuccessListener {
                            val username = it.child("username").value.toString()
                            if (username.isNotEmpty()) {
                                // Send username to MainActivity
                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("username", username)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Username not found.", Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed to retrieve username.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
