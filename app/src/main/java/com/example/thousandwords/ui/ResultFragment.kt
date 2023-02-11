package com.example.thousandwords.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.thousandwords.R
import com.example.thousandwords.databinding.FragmentResultBinding

class ResultFragment: Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding
    private val navArgs: ResultFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)

        binding.tvFinishResult.text = navArgs.check + "/" + navArgs.result
    }

}