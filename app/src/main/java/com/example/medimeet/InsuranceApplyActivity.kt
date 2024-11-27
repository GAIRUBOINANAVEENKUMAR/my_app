package com.example.medimeet

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InsuranceApplyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insurance_apply)

        // Get the policy name from the Intent
        val policyName = intent.getStringExtra("POLICY_NAME")

        // Set the policy name in a TextView (assuming you have a TextView in activity_insurance_apply.xml)
        val policyNameTextView: TextView = findViewById(R.id.policyNameTextView)
        policyNameTextView.text = "Applying for: $policyName"
    }
}
