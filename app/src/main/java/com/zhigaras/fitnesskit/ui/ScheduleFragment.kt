package com.zhigaras.fitnesskit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zhigaras.fitnesskit.ProvideViewModel
import com.zhigaras.fitnesskit.R
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
        
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observe(viewLifecycleOwner) {
                    view.findViewById<TextView>(R.id.test_text).text = it.data.toString()
                }
            }
        }
        
    }
}