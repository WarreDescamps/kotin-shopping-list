package com.example.oefstarter.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oefstarter.databinding.FragmentMainBinding
import com.example.oefstarter.screens.ShoppingListAdapter
import com.example.oefstarter.screens.ShoppingListOnLongClickListener

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        binding.recyclerView.adapter = viewModel.adapter
        viewModel.shoppingList.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.adapter.submitList(it)
            }
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.add.setOnClickListener { onAdd() }
        return binding.root
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