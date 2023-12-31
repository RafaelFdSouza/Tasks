package com.example.tasks.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tasks.R
import com.example.tasks.databinding.FragmentRecoverAccountBinding
import com.example.tasks.databinding.FragmentRegisterBinding
import com.example.tasks.utils.initToolbar
import com.example.tasks.utils.showBottomSheet


class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener() {
        binding.btnRecover.setOnClickListener {
            validateData()
        }
    }


    private fun validateData() {
        val email = binding.edtEmail.text.toString().trim()

        if (email.isNotEmpty()) {

            Toast.makeText(
                requireContext(),
                "Uma mensagem foi enviada para seu email",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            showBottomSheet(message = getString(R.string.email_recover_account))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}