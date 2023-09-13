package com.example.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tasks.R
import com.example.tasks.databinding.FragmentFormTaskBinding
import com.example.tasks.utils.initToolbar
import com.example.tasks.utils.showBottomSheet


class FormTaskFragment : Fragment() {
    private var _binding: FragmentFormTaskBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener() {
        binding.btnSave.setOnClickListener {
            validateData()
        }
    }


    private fun validateData() {
        val description = binding.edtDescription.text.toString().trim()

        if (description.isNotEmpty()) {
           Toast.makeText(requireContext(), "OK", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(message = R.string.description_empty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}