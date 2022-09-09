package com.garibyan.armen.tbc_classwork_7.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.garibyan.armen.tbc_classwork_7.databinding.ActiveCoursesItemBinding
import com.garibyan.armen.tbc_classwork_7.network.model.ResponceModel

class ActiveCoursesAdapter :
    ListAdapter<ResponceModel.ActiveCourses, ActiveCoursesAdapter.ActiveCoursesViewHolder>(
        ActiveCoursesCallBack()
    ) {

    inner class ActiveCoursesViewHolder(private val binding: ActiveCoursesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activeCourses: ResponceModel.ActiveCourses) = with(binding) {
            img.load(activeCourses.image)
            img.background.setTint(Color.parseColor("#${activeCourses.mainColor}"))

            tvBookingTime.text = "Booked for ${activeCourses.bookingTime}"
            tvBookingTime.setTextColor(Color.parseColor("#${activeCourses.mainColor}"))
            tvBookingTime2.text = "Booked for ${activeCourses.bookingTime}"
            tvBookingTime2.setTextColor(Color.parseColor("#${activeCourses.mainColor}"))

            progressBar.progress = activeCourses.playButtonColorPercent.toInt()
            progressBar.setIndicatorColor(Color.parseColor("#${activeCourses.mainColor}"))

            layout.setBackgroundColor(Color.parseColor("#${activeCourses.mainColor}"))
            layout.background.alpha = activeCourses.backgroundColorPercent.toInt()

            //btnPlay.imageTintList = ColorStateList.valueOf(Color.parseColor("#${activeCourses.mainColor}"))
        }
    }

    class ActiveCoursesCallBack : DiffUtil.ItemCallback<ResponceModel.ActiveCourses>() {
        override fun areItemsTheSame(
            oldItem: ResponceModel.ActiveCourses,
            newItem: ResponceModel.ActiveCourses
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResponceModel.ActiveCourses,
            newItem: ResponceModel.ActiveCourses
        ) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActiveCoursesViewHolder(
        ActiveCoursesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ActiveCoursesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}