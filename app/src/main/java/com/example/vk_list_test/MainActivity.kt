package com.example.vk_list_test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.vk_list_test.presentation.fragments.product_detail.ProductDetailFragment
import com.example.vk_list_test.presentation.fragments.product_list.ProductListFragment
import com.example.vk_list_test.presentation.navigation.NavigationArgs
import com.example.vk_list_test.presentation.navigation.NavigationRoutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = NavigationRoutes.productList
        ) {
            fragment<ProductListFragment>(NavigationRoutes.productList)
            fragment<ProductDetailFragment>("${NavigationRoutes.productDetail}/{${NavigationArgs.productId}}") {
                argument(NavigationArgs.productId) {
                    type = NavType.IntType
                    nullable = false
                }
            }
        }
    }
}