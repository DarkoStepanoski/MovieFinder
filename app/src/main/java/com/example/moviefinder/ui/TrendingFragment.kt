package com.example.moviefinder.ui

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviefinder.R
import com.example.moviefinder.adapters.MultimediaAdapter
import com.example.moviefinder.databinding.FragmentTrendingBinding
import com.example.moviefinder.util.Constants.Companion.SEARCH_QUERY_TIME_DELAY
import com.example.moviefinder.viewmodels.TrendingViewModel
import com.example.moviefinder.viewmodels.TrendingViewModelFactory
import kotlinx.coroutines.*

class TrendingFragment : Fragment() {

    private val viewModel: TrendingViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, TrendingViewModelFactory(activity.application))
            .get(TrendingViewModel::class.java)
    }

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTrendingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_trending, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.showSearchMovies.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                viewModel.showSearchedMovies(it)
                viewModel.doneShowingSearchedMovies()
            }
        })

        viewModel.showTrendingMovies.observe(viewLifecycleOwner, Observer {
            if (it == true){
                viewModel.trendingMovies()
                viewModel.doneShowingTrendingMovies()
            }
        })

        binding.recyclerView.adapter = MultimediaAdapter(MultimediaAdapter.OnClickListener{
            viewModel.displayMultimediaDetails(it)
        })

        viewModel.navigateToMultimediaDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(TrendingFragmentDirections
                    .actionTrendingFragmentToDetailsFragment(it))
                viewModel.displayMultimediaDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        var menuItem: MenuItem = menu!!.findItem(R.id.search)
        var searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search movies"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.isNotEmpty()){
                        viewModel.searchForAMovie(query)
                    }
                }

                if (query.isNullOrEmpty()) {
                    viewModel.showTrendingMovies()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    /**
                     * We delay the search for avoiding too many requests.
                      */
                    delay(SEARCH_QUERY_TIME_DELAY)
                    newText?.let {
                        if (newText.isNotEmpty()){
                            viewModel.searchForAMovie(newText)
                        }
                    }

                    if (newText!!.isNullOrEmpty()){
                        viewModel.showTrendingMovies()
                    }
                }
                return false
            }
        })

        val searchManager = requireContext().getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || return super.onOptionsItemSelected(item)
    }
}