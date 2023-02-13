package com.example.thousandwords.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.thousandwords.R
import com.example.thousandwords.databinding.DialogStartBinding

class GameDialog: DialogFragment(R.layout.dialog_start) {

    private lateinit var binding: DialogStartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogStartBinding.bind(view)

        binding.btnStartDialog.setOnClickListener {
            if (binding.etName.text.toString().isNotEmpty() && binding.etName.text.toString()[0] != '0') {

                onAddSuccess.invoke(binding.etName.text.toString())
                dismiss()
            }
        }
    }

    private var onAddSuccess: (quantity: String) -> Unit = {}
    fun setOnAddSuccessListener(onAddSuccess: (quantity: String) -> Unit) {
        this.onAddSuccess = onAddSuccess
    }
}