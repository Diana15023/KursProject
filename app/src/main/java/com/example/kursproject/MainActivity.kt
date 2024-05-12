package com.example.kursproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kursproject.databinding.ActivityMainBinding
import com.example.kursproject.fragments.AddOperationFragment
import com.example.kursproject.fragments.AnalyticsFragment
import com.example.kursproject.fragments.BudgetFragment
import com.example.kursproject.fragments.HistoryFragment
import com.example.kursproject.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this@MainActivity, Choise::class.java))
//            return // Прерываем выполнение кода, если пользователь не авторизован
        }



        // Navigation для переключения снизу
        val navView: BottomNavigationView = findViewById(R.id.bNav)
        val navController = findNavController(R.id.fragment)
        navView.setupWithNavController(navController)
    }

}


