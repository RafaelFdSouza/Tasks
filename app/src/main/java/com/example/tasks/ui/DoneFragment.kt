package com.example.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks.R
import com.example.tasks.data.model.Status
import com.example.tasks.data.model.Task
import com.example.tasks.databinding.FragmentDoingBinding
import com.example.tasks.databinding.FragmentDoneBinding
import com.example.tasks.ui.adapter.TaskAdapter


class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null

    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getTasks()
    }

    private fun initRecyclerView() {
        taskAdapter = TaskAdapter(requireContext())

        with(binding.rvTasks) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun getTasks() {
        val taskList = listOf(
            Task(id = "0", "Tarefa qualquer exemplo", Status.DONE),
            Task(id = "1", "Só exemplo", Status.DONE),
            Task(id = "2", "Assdasdasdoosdoso", Status.DONE),

            )
        taskAdapter.submitList(taskList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}