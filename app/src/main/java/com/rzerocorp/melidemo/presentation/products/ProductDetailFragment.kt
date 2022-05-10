package com.rzerocorp.melidemo.presentation.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rzerocorp.melidemo.R
import com.rzerocorp.melidemo.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val viewModel: ProductsViewModel by viewModels()
    lateinit var binding: FragmentProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = getString(R.string.product_detail_title)

        viewModel.product.observe(viewLifecycleOwner) {
            Log.d(this@ProductDetailFragment::class.java.canonicalName, it.title)
        }

        getInfo()

        return binding.root
    }

    private fun getInfo() {
        arguments?.getString("id").let {
            if (it != null) {
                viewModel.getProductByID(it)
            } else {
                Toast.makeText(requireContext(), getString(R.string.hello_blank_fragment),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}