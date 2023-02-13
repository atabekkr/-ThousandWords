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
            val dialog = GameDialog()
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)

            dialog.setOnAddSuccessListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToGameFragment(it)
                )
            }
        }
    }
}