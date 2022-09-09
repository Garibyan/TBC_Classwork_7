package com.garibyan.armen.tbc_classwork_7.repository

import android.util.Log
import com.garibyan.armen.tbc_classwork_7.network.ApiService
import com.garibyan.armen.tbc_classwork_7.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) : MainRepository {

    override fun getAllCourses() = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(apiService.getAllCourses()))
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    emit(
                        Resource.Error(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    )
                }
                else -> {
                    emit(Resource.Error(true, null, null))
                    Log.d("mylog", throwable.message.toString())
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}