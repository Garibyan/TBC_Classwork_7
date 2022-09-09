package com.garibyan.armen.tbc_classwork_7.network.model

import com.squareup.moshi.Json

data class ResponceModel(
    @Json(name = "new_courses")
    val newCourses: List<NewCourses>,
    @Json(name = "active_courses")
    val activeCourses: List<ActiveCourses>
){
    data class NewCourses(
        val id: String,
        @Json(name = "icon_type")
        val iconType: String,
        val duration: String,
        val title: String,
        val question: String,
        @Json(name = "main_color")
        val mainColor: String
    )

    data class ActiveCourses(
        val id: String,
        @Json(name = "booking_time")
        val bookingTime: String,
        val title: String,
        @Json(name = "main_color")
        val mainColor: String,
        @Json(name = "background_color_percent")
        val backgroundColorPercent: String,
        @Json(name = "play_button_color_percent")
        val playButtonColorPercent: String,
        val image: String
    )
}
