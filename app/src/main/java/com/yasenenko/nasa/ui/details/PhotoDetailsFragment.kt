package com.yasenenko.nasa.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yasenenko.nasa.R
import com.yasenenko.nasa.common.BindingFragment
import com.yasenenko.nasa.common.getParcelize
import com.yasenenko.nasa.databinding.FragmentDetailsBinding
import com.yasenenko.nasa.domain.model.Photo

class PhotoDetailsFragment : BindingFragment<FragmentDetailsBinding>() {

    override val layout: Int
        get() = R.layout.fragment_details

    private val photo: Photo
        get() = checkNotNull(arguments?.getParcelize(EXTRA_PHOTO_KEY, Photo::class.java)) {
            "Passing data to the fragment is mandatory using the DetailsFragment.EXTRA_PHOTO_KEY key"
        }

    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModels {
        PhotoDetailsViewModel.Factory(photo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = photoDetailsViewModel

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        const val EXTRA_PHOTO_KEY = "photo"
    }
}