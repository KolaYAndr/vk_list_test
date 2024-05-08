package com.example.vk_list_test.presentation.fragments.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.vk_list_test.databinding.FragmentProductListBinding
import com.example.vk_list_test.presentation.fragments.product_list.recycler.ProductAdapter
import com.example.vk_list_test.presentation.fragments.product_list.recycler.ProductItemDecoration
import com.example.vk_list_test.presentation.navigation.NavigationRoutes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductAdapter
    private val viewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        collectViewModelData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        adapter = ProductAdapter()
//        adapter.setOnItemClickListener {
//            findNavController().navigate("${NavigationRoutes.productDetail}/${it.id}")
//        }
        binding.productRecyclerView.adapter = adapter
        binding.productRecyclerView.addItemDecoration(ProductItemDecoration(16))

        adapter.addLoadStateListener { loadStates ->
            when (loadStates.refresh) {
                is LoadState.Loading -> {
                    binding.listProgressBar.visibility = View.VISIBLE
                    binding.productRecyclerView.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                }
                is LoadState.NotLoading -> {
                    binding.listProgressBar.visibility = View.GONE
                    binding.productRecyclerView.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.GONE
                }
                else -> {
                    binding.listProgressBar.visibility = View.GONE
                    binding.productRecyclerView.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun collectViewModelData() {
        lifecycleScope.launch {
            viewModel.products.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}