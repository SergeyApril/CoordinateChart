package com.example.coordinatechart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.coordinatechart.App
import com.example.coordinatechart.R
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.injectMainActivity(this)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostMainContainer) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.coordinate_nav_graph)
        graph.setStartDestination(R.id.startFragment)
        navController = navHostFragment.navController
        navController?.setGraph(graph, intent.extras)
    }
}