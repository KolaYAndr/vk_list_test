package com.example.vk_list_test.presentation.fragments.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.vk_list_test.R
import com.example.vk_list_test.databinding.FragmentProductDetailBinding
import com.example.vk_list_test.presentation.fragments.product_detail.view_pager.DetailViewPagerAdapter
import com.example.vk_list_test.presentation.navigation.NavigationArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DetailViewPagerAdapter

    private val productId = requireArguments().getInt(NavigationArgs.productId)

    private val detailViewModel by viewModels<ProductDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getProduct(productId)
        setProductInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setProductInfo() {
        lifecycleScope.launch {
            detailViewModel.product.collectLatest { state ->
                when (state) {
                    null -> {
                        binding.detailProgressBar.visibility = View.VISIBLE
                        binding.productViewPager.visibility = View.GONE
                        binding.productTitle.visibility = View.GONE
                        binding.productCost.visibility = View.GONE
                        binding.productBody.visibility = View.GONE
                    }
                    else -> {
                        binding.detailProgressBar.visibility = View.GONE
                        adapter = DetailViewPagerAdapter().apply {
                            submitList(state.images)
                        }
                        binding.productViewPager.adapter = adapter
                        binding.productTitle.text = state.title
                        binding.productCost.text = resources.getString(R.string.product_price, state.price)
                        binding.productBody.text = state.description
                    }
                }

            }
        }

    }
}