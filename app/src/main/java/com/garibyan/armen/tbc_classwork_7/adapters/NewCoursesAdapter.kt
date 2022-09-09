package com.garibyan.armen.tbc_classwork_7.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_classwork_7.databinding.NewCoursesItemBinding
import com.garibyan.armen.tbc_classwork_7.network.model.ResponceModel

class NewCoursesAdapter :
    ListAdapter<ResponceModel.NewCourses, NewCoursesAdapter.NewCoursesViewHolder>(NewCoursesCallBack()) {

    inner class NewCoursesViewHolder(private val binding: NewCoursesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newCourses: ResponceModel.NewCourses) = with(binding) {
            tvTitle.text = newCourses.title
            tvQuestion.text = newCourses.question
            root.setCardBackgroundColor(Color.parseColor("#${newCourses.mainColor}"))
        }
    }

    class NewCoursesCallBack : DiffUtil.ItemCallback<ResponceModel.NewCourses>() {
        override fun areItemsTheSame(
            oldItem: ResponceModel.NewCourses,
            newItem: ResponceModel.NewCourses
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResponceModel.NewCourses,
            newItem: ResponceModel.NewCourses
        ) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewCoursesViewHolder(
        NewCoursesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewCoursesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}