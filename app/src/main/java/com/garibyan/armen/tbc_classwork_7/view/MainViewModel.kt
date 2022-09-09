package com.garibyan.armen.tbc_classwork_7.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garibyan.armen.tbc_classwork_7.network.Resource
import com.garibyan.armen.tbc_classwork_7.network.model.ResponceModel
import com.garibyan.armen.tbc_classwork_7.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val _coursesFlow = MutableStateFlow<Resource<ResponceModel>>(Resource.Loading)
    val  coursesFlow = _coursesFlow.asStateFlow()

    init {
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModelScope.launch {
            repository.getAllCourses().collect {
                _coursesFlow.value = it
            }
        }
    }
}