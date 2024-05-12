package com.example.kursproject.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kursproject.CategoryActivity
import com.example.kursproject.Choise
import com.example.kursproject.R
import com.example.kursproject.databinding.FragmentSettingsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel

        auth = Firebase.auth
        binding.textViewEmail.text = auth.currentUser?.email

        binding.buttonLogout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(requireActivity(), Choise::class.java))
        }

//        binding.textViewChangePassword.text = auth.currentUser?.uid

        binding.textViewChangeCategories.setOnClickListener{
            startActivity(Intent(requireActivity(), CategoryActivity::class.java))
        }
    }

}