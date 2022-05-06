package com.rzerocorp.melidemo.presentation.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.rzerocorp.melidemo.R
import com.rzerocorp.melidemo.databinding.ProductsFragmentBinding

class ProductsFragment : Fragment() {
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var binding: ProductsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductsFragmentBinding.inflate(LayoutInflater.from(requireContext()))

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Escribe aquí para buscar..."

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

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