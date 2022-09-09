package com.garibyan.armen.tbc_classwork_7.network

import com.garibyan.armen.tbc_classwork_7.network.model.ResponceModel
import retrofit2.http.GET

interface ApiService {

    @GET("4167a598-b68c-420f-b6e1-fef68b89a10d")
    suspend fun getAllCourses(): ResponceModel
}