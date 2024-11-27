package com.example.medimeet

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlin.concurrent.thread

class AppointmentActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var dobEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var mobileNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var phonePeImageView: ImageView
    private lateinit var uploadImageView: ImageView
    private lateinit var bookNowButton: Button
    private lateinit var successMessageLayout: RelativeLayout
    private lateinit var confirmationIcon: ImageView
    private  lateinit var retrivebtn:Button
private lateinit var db:FirebaseDatabase
    private val REQUEST_CODE_PICK_IMAGE = 1001
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        // Initialize Views
//        retrivebtn=findViewById(R.id.ret)
        firstNameEditText = findViewById(R.id.firstNameEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
        dobEditText = findViewById(R.id.dobEditText)
        ageEditText = findViewById(R.id.ageEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText)
        emailEditText = findViewById(R.id.emailEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        phonePeImageView = findViewById(R.id.phonePeImageView)
        uploadImageView = findViewById(R.id.uploadImageView)
        bookNowButton = findViewById(R.id.bookNowButton)
//        successMessageLayout = findViewById(R.id.successMessageLayout)
//        confirmationIcon = findViewById(R.id.confirmationIcon)
        db = FirebaseDatabase.getInstance()

        // Set up ImageView click listeners
        phonePeImageView.setOnClickListener {
            // Handle PhonePe scanner functionality here
        }

        uploadImageView.setOnClickListener {
            // Open file picker to select image
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
        }

        // Set up Book Now button click listener
        bookNowButton.setOnClickListener {
            handleBooking()
        }
    }

    private fun handleBooking() {
        // Validate inputs (You can add more validation here)
        if (firstNameEditText.text.isEmpty() ||
            surnameEditText.text.isEmpty() ||
            dobEditText.text.isEmpty() ||
            ageEditText.text.isEmpty() ||
            mobileNumberEditText.text.isEmpty() ||
            emailEditText.text.isEmpty() ||
            descriptionEditText.text.isEmpty()) {

            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }
            Toast.makeText(this, "Your Appointment was Sucessfully Booked", Toast.LENGTH_SHORT).show()
            val name = firstNameEditText.text.toString()
            val sur = surnameEditText.text.toString()
            val dob = dobEditText.text.toString()
            val problem = descriptionEditText.text.toString()
            val list = StudentModel(name,sur,dob,problem)
            db.reference.child("gnk").push().setValue(list)

           val i=Intent(this,MainActivity::class.java)
          startActivity(i)
//        // Show success message
//        successMessageLayout.visibility = View.VISIBLE

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                // Handle selected image
                selectedImageUri = uri
                uploadImageView.setImageURI(uri)
            }
        }
    }
}
