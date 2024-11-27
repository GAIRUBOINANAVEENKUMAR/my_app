package com.example.medimeet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.medimeet.R

class InsuranceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insurance)

        // Find and set onClickListeners for each "APPLY" button
        val btnApply1: Button = findViewById(R.id.btnApply1)
        val btnApply2: Button = findViewById(R.id.btnApply2)
        val btnApply3: Button = findViewById(R.id.btnApply3)
        val btnApply4: Button = findViewById(R.id.btnApply4)
        val btnApply5: Button = findViewById(R.id.btnApply5)
        val btnApply6: Button = findViewById(R.id.btnApply6)
        val btnApply7: Button = findViewById(R.id.btnApply7)
        val btnApply8: Button = findViewById(R.id.btnApply8)
        val btnApply9: Button = findViewById(R.id.btnApply9)
        val btnApply10: Button = findViewById(R.id.btnApply10)

        // Function to start the InsuranceApplyActivity
        fun openInsuranceApplyActivity(policyName: String) {
            val intent = Intent(this, InsuranceApplyActivity::class.java)
            intent.putExtra("POLICY_NAME", policyName)  // Pass the policy name to the next activity
            startActivity(intent)
        }

        // Set onClickListener for each button and call the function
        btnApply1.setOnClickListener {
            openInsuranceApplyActivity("Mediclaim Policy")
        }

        btnApply2.setOnClickListener {
            openInsuranceApplyActivity("Critical Illness Cover")
        }

        btnApply3.setOnClickListener {
            openInsuranceApplyActivity("Personal Accident Insurance")
        }

        btnApply4.setOnClickListener {
            openInsuranceApplyActivity("Senior Citizen Health Insurance")
        }

        btnApply5.setOnClickListener {
            openInsuranceApplyActivity("Family Floater Health Plan")
        }

        btnApply6.setOnClickListener {
            openInsuranceApplyActivity("Group Health Insurance")
        }

        btnApply7.setOnClickListener {
            openInsuranceApplyActivity("Disease-Specific Health Insurance")
        }

        btnApply8.setOnClickListener {
            openInsuranceApplyActivity("Maternity Health Insurance")
        }

        btnApply9.setOnClickListener {
            openInsuranceApplyActivity("Top-Up Health Insurance")
        }

        btnApply10.setOnClickListener {
            openInsuranceApplyActivity("Surgical Cash Insurance")
        }
    }
}
