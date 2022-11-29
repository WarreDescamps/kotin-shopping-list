package com.example.oefstarter.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oefstarter.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = viewModel.adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.add.setOnClickListener { onAdd() }
    }

    private fun onAdd() {
        viewModel.addItem(binding.etItem.text.toString(), binding.etShop.text.toString())
        clearTextFields()
    }

    private fun clearTextFields() {
        binding.etItem.setText("")
        binding.etShop.setText("")
    }
}