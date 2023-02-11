package com.example.thousandwords.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thousandwords.R
import com.example.thousandwords.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.btnStart.setOnClickListener {
            if (binding.etName.text.isNotEmpty() && binding.etName.text[0] != '0') {

                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToGameFragment(binding.etName.text.toString())
                )
            }
        }
    }
}