package com.example.medimeet
import android.graphics.Color


import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BuyMedicineActivity : AppCompatActivity() {
    lateinit var buyNowBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine)

        var buynowbtn1:ImageView=findViewById(R.id.cartbtn1)
        var buynowbtn2:ImageView=findViewById(R.id.cartbtn2)
        var buynowbtn3:ImageView=findViewById(R.id.cartbtn3)
        var buynowbtn4:ImageView=findViewById(R.id.cartbtn4)
        var buynowbtn5:ImageView=findViewById(R.id.cartbtn5)
        var buynowbtn6:ImageView=findViewById(R.id.cartbtn6)
        var buynowbtn7:ImageView=findViewById(R.id.cartbtn7)
        var buynowbtn8:ImageView=findViewById(R.id.cartbtn8)
        var buynowbtn9:ImageView=findViewById(R.id.cartbtn9)
        val btnBuyNow: Button = findViewById(R.id.btnBuyNow)

        buyNowBtn=findViewById(R.id.cartbtn)

        // Handle Buy Now button click
        buyNowBtn.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn1.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn2.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn3.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn4.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn5.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn6.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn7.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn8.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }
        buynowbtn9.setOnClickListener {
            Toast.makeText(this, "Medicine added to cart!", Toast.LENGTH_SHORT).show()
        }



        btnBuyNow.setOnClickListener{
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }

        val colorAnimator = ValueAnimator.ofArgb(Color.BLUE, Color.RED)
        colorAnimator.duration = 2000 // Duration for the animation
        colorAnimator.repeatCount = ValueAnimator.INFINITE // Repeat indefinitely
        colorAnimator.addUpdateListener { animator ->
            btnBuyNow.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimator.start()


     }
}
