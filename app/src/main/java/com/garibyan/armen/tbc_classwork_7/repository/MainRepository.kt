package com.garibyan.armen.tbc_classwork_7.repository

import com.garibyan.armen.tbc_classwork_7.network.Resource
import com.garibyan.armen.tbc_classwork_7.network.model.ResponceModel
import kotlinx.coroutines.flow.Flow


interface MainRepository {
    fun getAllCourses(): Flow<Resource<ResponceModel>>

}