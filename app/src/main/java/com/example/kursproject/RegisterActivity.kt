package com.example.kursproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kursproject.databinding.ActivityLoginBinding
import com.example.kursproject.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener{
            if (binding.emailEt.text.isEmpty() || binding.passwordEt.text.isEmpty() || binding.usernameEt.text.isEmpty()) {
                Toast.makeText(applicationContext, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEt.text.toString(), binding.passwordEt.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userInfo = HashMap<String, String>()
                            userInfo.put("email", binding.emailEt.text.toString())
                            userInfo.put("username", binding.usernameEt.text.toString())
                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .setValue(userInfo)

                            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                        }
                    }

            }
        }
    }
}