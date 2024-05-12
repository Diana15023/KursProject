package com.example.kursproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kursproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            if (binding.emailEt.text.isEmpty() || binding.passwordEt.text.isEmpty()) {
                Toast.makeText(applicationContext, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
            }
            else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEt.text.toString(), binding.passwordEt.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }
            }
        }

    }


}