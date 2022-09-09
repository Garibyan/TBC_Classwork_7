package com.garibyan.armen.tbc_classwork_7.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_classwork_7.adapters.ActiveCoursesAdapter
import com.garibyan.armen.tbc_classwork_7.adapters.NewCoursesAdapter
import com.garibyan.armen.tbc_classwork_7.databinding.ActivityMainBinding
import com.garibyan.armen.tbc_classwork_7.network.Resource
import com.garibyan.armen.tbc_classwork_7.network.model.ResponceModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val newCourseAdapter: NewCoursesAdapter by lazy { NewCoursesAdapter() }
    private val activeCourseAdapter: ActiveCoursesAdapter by lazy { ActiveCoursesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch() {
            viewModel.coursesFlow.collectLatest {
                when (it) {
                    is Resource.Success -> successfulState(
                        it.value.newCourses,
                        it.value.activeCourses
                    )
                    is Resource.Loading -> loadingState()
                }
            }
        }
    }

    private fun successfulState(
        newCourses: List<ResponceModel.NewCourses>,
        activeCourses: List<ResponceModel.ActiveCourses>
    ) = with(binding) {
        initRecyclerView(newCourses, activeCourses)
        scrollView2.visibility = View.VISIBLE
        tvTitle.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun loadingState() = with(binding) {
        scrollView2.visibility = View.GONE
        tvTitle.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }


    private fun initRecyclerView(
        newCourses: List<ResponceModel.NewCourses>,
        activeCourses: List<ResponceModel.ActiveCourses>
    ) = with(binding) {
        rvNewCourses.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        rvNewCourses.adapter = newCourseAdapter
        newCourseAdapter.submitList(newCourses)

        rvActiveCourses.layoutManager = LinearLayoutManager(this@MainActivity)
        rvActiveCourses.adapter = activeCourseAdapter
        activeCourseAdapter.submitList(activeCourses)
    }
}