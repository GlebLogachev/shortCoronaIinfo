package com.glogachev.sigmatesttask.ui.countryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.glogachev.sigmatesttask.App
import com.glogachev.sigmatesttask.databinding.FragmentCountryDetailsBinding
import com.glogachev.sigmatesttask.domain.CoronaRepository
import javax.inject.Inject

class CountryDetailsFragment : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: CoronaRepository

    private val args: CountryDetailsFragmentArgs by navArgs<CountryDetailsFragmentArgs>()

    private val viewModel: CountryDetailsViewModel by viewModels<CountryDetailsViewModel> {
        CountryDetailsViewModelFactory(repository)
    }

    private val countryDetailsAdapter = CountryDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.component.inject(this)
        viewModel.obtainCountryDetails(args.countryName)
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.countryDetailsList.adapter = countryDetailsAdapter
        binding.countryDetailsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.state.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it is CountryDetailsState.Loading
            when (it) {
                CountryDetailsState.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is CountryDetailsState.Success -> {

                    countryDetailsAdapter.submitList(it.initialList)
                    binding.errorScreen.isVisible = it.initialList.isEmpty()
                }
            }
        }

        binding.btnRetryLoading.setOnClickListener {
            viewModel.obtainCountryDetails(args.countryName)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.obtainCountryDetails(args.countryName)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}