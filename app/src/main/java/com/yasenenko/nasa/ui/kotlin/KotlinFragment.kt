package com.yasenenko.nasa.ui.kotlin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yasenenko.nasa.R
import com.yasenenko.nasa.common.BindingFragment
import com.yasenenko.nasa.databinding.FragmentKotlinBinding
import com.yasenenko.nasa.ui.details.PhotoDetailsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class KotlinFragment : BindingFragment<FragmentKotlinBinding>() {

    override val layout: Int
        get() = R.layout.fragment_kotlin

    private val kotlinViewModel by viewModels<KotlinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = kotlinViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        kotlinViewModel.networkConnection.observe(viewLifecycleOwner) { hasConnection ->
            if (hasConnection) {
                kotlinViewModel.fetchPhotos()
            }
        }

        kotlinViewModel.error.observe(viewLifecycleOwner) {

            Snackbar.make(view, it.getMessage(requireContext()), Snackbar.LENGTH_LONG).show()
        }

        kotlinViewModel.itemClick.observe(viewLifecycleOwner) {

            findNavController().navigate(R.id.action_kotlin_to_details, Bundle().apply {
                putParcelable(PhotoDetailsFragment.EXTRA_PHOTO_KEY, it)
            })
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            val itemId = menuItem.itemId
            if (itemId == R.id.sort) {
                kotlinViewModel.changeSort()
                return@setOnMenuItemClickListener true
            }
            return@setOnMenuItemClickListener false
        }
    }
}