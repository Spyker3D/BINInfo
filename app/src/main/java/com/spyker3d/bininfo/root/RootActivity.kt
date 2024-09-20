package com.spyker3d.bininfo.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.spyker3d.bininfo.R
import com.spyker3d.bininfo.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.searchFragment, R.id.historyFragment -> {
                    binding.bottomNavigationView.isVisible = true
                    binding.horizontalLine.isVisible = true
                }

                else -> {
                    binding.bottomNavigationView.isVisible = false
                    binding.horizontalLine.isVisible = false
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}