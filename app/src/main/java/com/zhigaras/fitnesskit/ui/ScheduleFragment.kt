package com.zhigaras.fitnesskit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.zhigaras.fitnesskit.ProvideViewModel
import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.ui.adapter.ScheduleAdapter
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment() {
    
    private lateinit var viewModel: ScheduleViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(
            ScheduleViewModel::class.java,
            this
        )
        val scheduleAdapter = ScheduleAdapter()
        view.findViewById<RecyclerView>(R.id.recycler).apply {
            adapter = scheduleAdapter
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observe(viewLifecycleOwner) {
                    it.data?.let { scheduleAdapter.setData(it) }
                }
            }
        }
    }
}