package com.glogachev.sigmatesttask.ui.countriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.glogachev.sigmatesttask.App
import com.glogachev.sigmatesttask.databinding.FragmentCountriesListBinding
import com.glogachev.sigmatesttask.domain.CoronaRepository
import javax.inject.Inject


class CountryListFragment : Fragment() {

    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: CoronaRepository

    private val viewModel: CountryListViewModel by viewModels {
        CountryListViewModelFactory(repository)
    }
    private val countriesAdapter = CountriesAdapter {
        val action =
            CountryListFragmentDirections.actionCountriesListFragmentToCountryDetailsFragment(it)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountriesListBinding.inflate(inflater, container, false)
        App.component.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.countriesList.adapter = countriesAdapter
        binding.countriesList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        setupCallbacks()

        viewModel.state.observe(viewLifecycleOwner) {

            binding.refresh.isRefreshing = it is CountryListState.Loading

            when (it) {
                is CountryListState.Success -> {
                    binding.errorScreen.isVisible = it.initialList.isEmpty()
                    binding.successScreen.isVisible = it.initialList.isNotEmpty()
                    countriesAdapter.submitList(it.currentList)

                }
                CountryListState.Loading -> {
                    binding.refresh.isRefreshing = true
                }
            }
        }
    }

    private fun setupCallbacks() {
        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
        }

        binding.btnRetryLoading.setOnClickListener {
            viewModel.refresh()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.newSearchValue(newText ?: "")
                return false
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}