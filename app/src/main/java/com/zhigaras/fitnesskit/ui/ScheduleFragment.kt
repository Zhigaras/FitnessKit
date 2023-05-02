package com.zhigaras.fitnesskit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zhigaras.fitnesskit.ProvideViewModel
import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.databinding.FragmentScheduleBinding
import com.zhigaras.fitnesskit.domain.LoadState
import com.zhigaras.fitnesskit.ui.adapter.ScheduleAdapter

class ScheduleFragment : Fragment() {
    
    private lateinit var viewModel: ScheduleViewModel
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scheduleAdapter = ScheduleAdapter()
        
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(
            ScheduleViewModel::class.java,
            this
        )
        binding.recycler.adapter = scheduleAdapter
        
        binding.recycler.scrollToPosition(viewModel.restoreScrollState())
        
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchSchedule()
        }
        
        viewModel.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it is LoadState.Loading
            it.data?.let {
                scheduleAdapter.setData(it)
            }
            it.errorMessage?.let { error ->
                Snackbar.make(
                    binding.snackbarLayout,
                    error.asString(requireContext()),
                    Snackbar.LENGTH_SHORT
                )
                    .setAnchorView(binding.snackbarLayout)
                    .setAction(R.string.retry) {
                        viewModel.fetchSchedule()
                    }.show()
            }
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveScrollState(
            (binding.recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        )
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}