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
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.glide.GlideImageLoaderFactory
import com.rzerocorp.melidemo.R
import com.rzerocorp.melidemo.databinding.FragmentProductDetailBinding
import com.rzerocorp.melidemo.utils.formatAsCurrency
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
        val imageUrls: ArrayList<String> = ArrayList()

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = getString(R.string.product_detail_title)

        binding.imageSlider.isPageIndicatorVisible = false

        viewModel.product.observe(viewLifecycleOwner) {
            binding.txtProductTitle.text = it.title
            binding.txtDescription.text = it.description?.plain_text
            binding.txtPrice.text = it.price.formatAsCurrency()
            binding.txtAvailableStockQuantity.text =
                String.format("%d " + getString(R.string.available), it.available_quantity)
            binding.txtNickname.text =
                String.format(getString(R.string.username) + ": %s", it.seller?.nickname)
            binding.txtSellerLocation.text =
                String.format(getString(R.string.location) + ": %s, %s",
                    it.seller_address.city.name, it.seller_address.state.name)

            it.pictures?.forEach { picture ->
                imageUrls.add(picture.secure_url)
            }

            binding.imageSlider.adapter = SliderAdapter(requireContext(), GlideImageLoaderFactory(), imageUrls)
            binding.imageSlider.stopAutoLooping()
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
                findNavController().navigate(R.id.action_productDetailFragment_to_productsFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}