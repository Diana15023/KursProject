package com.example.kursproject.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kursproject.R

class AddOperationFragment : Fragment() {

    companion object {
        fun newInstance() = AddOperationFragment()
    }

    private lateinit var viewModel: AddOperationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add_operation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddOperationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}