package com.hiqmalism.mysubmission

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupView()
        setupAction()
    }

    private fun setupView() {
        supportActionBar?.hide()
    }

    private fun setupAction() {
        val btnMain: Button = findViewById(R.id.btn_main)
        btnMain.setOnClickListener {
            val intentDetail = Intent(this, MainActivity::class.java)
            startActivity(intentDetail)
        }
    }
}