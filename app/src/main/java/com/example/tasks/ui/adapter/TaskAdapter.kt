package com.example.tasks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.data.model.Status
import com.example.tasks.data.model.Task
import com.example.tasks.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
) : ListAdapter<Task, TaskAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        val SELECT_BACK: Int = 1
        val SELECT_REMOVE: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id && oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem && oldItem.description == newItem.description
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.binding.tvTaskDescription.text = task.description

        setIndicators(task, holder)

        holder.binding.btnDelete.setOnClickListener {
            optionSelected(task, SELECT_REMOVE)

        }
        holder.binding.btnEdit.setOnClickListener {
            optionSelected(task, SELECT_EDIT)
        }
        holder.binding.btnDetails.setOnClickListener {
            optionSelected(task, SELECT_DETAILS)
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when(option){
            SELECT_EDIT-> {
                Toast.makeText(context, "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }

        SELECT_REMOVE -> {
                Toast.makeText(context, "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            SELECT_DETAILS -> {
                Toast.makeText(context, "Details ${task.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setIndicators(task: Task, holder: ViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.btnBack.isVisible = false

                holder.binding.btnNext.setOnClickListener {
                    optionSelected(task, SELECT_NEXT)
                }
            }

            Status.DOING -> {
                holder.binding.btnBack.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_btnBack
                    )
                )
                holder.binding.btnNext.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_btnNext
                    )
                )

                holder.binding.btnBack.setOnClickListener {
                    optionSelected(task, SELECT_BACK)
                }
                holder.binding.btnNext.setOnClickListener {
                    optionSelected(task, SELECT_NEXT)
                }

            }

            Status.DONE -> {
                holder.binding.btnNext.isVisible = false

                holder.binding.btnBack.setOnClickListener {
                    optionSelected(task, SELECT_BACK)
                }
            }

        }
    }

    inner class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {


    }
}