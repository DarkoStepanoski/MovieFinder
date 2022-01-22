package com.example.moviefinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviefinder.R
import com.example.moviefinder.databinding.FragmentDetailsBinding
import com.example.moviefinder.viewmodels.DetailsViewModel
import com.example.moviefinder.viewmodels.DetailsViewModelFactory

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)

        binding.lifecycleOwner = this

        val movie = DetailsFragmentArgs.fromBundle(requireArguments()).movie
        val viewModelFactory = DetailsViewModelFactory(movie)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        return binding.root
    }
}