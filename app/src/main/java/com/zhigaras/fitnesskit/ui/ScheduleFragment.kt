package com.zhigaras.fitnesskit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhigaras.fitnesskit.ProvideViewModel
import com.zhigaras.fitnesskit.R

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
        
    }
}