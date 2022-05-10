package com.rzerocorp.melidemo.presentation.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rzerocorp.melidemo.R
import com.rzerocorp.melidemo.databinding.ProductsFragmentBinding
import com.rzerocorp.melidemo.presentation.products.adapters.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var binding: ProductsFragmentBinding
    private val productsAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = getString(R.string.product_detail_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductsFragmentBinding.inflate(LayoutInflater.from(requireContext()))

        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productsAdapter
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Escribe aquí para buscar..."

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.fetchProducts2(it).collectLatest {
                            productsAdapter.submitData(it)
                        }
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    Log.d(this@ProductsFragment.tag, it)
                }
                
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

}