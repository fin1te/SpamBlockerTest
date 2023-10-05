package com.utkarsha.spamblocker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utkarsha.spamblocker.R
import com.utkarsha.spamblocker.adapter.SmsAdapter
import com.utkarsha.spamblocker.databinding.FragmentCleanSmsBinding
import com.utkarsha.spamblocker.repository.SmsRepo

class CleanSmsFragment : Fragment() {

    private var _binding: FragmentCleanSmsBinding? = null
    private val binding get() = _binding!!
    private lateinit var cleanSmsAdapter: SmsAdapter
    private lateinit var cleanSmsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentCleanSmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        cleanSmsRecyclerView = binding.cleanSmsRecyclerView
        cleanSmsRecyclerView.setHasFixedSize(true)
        cleanSmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cleanSmsAdapter = SmsAdapter(SmsRepo.getMockSms())
        cleanSmsRecyclerView.adapter = cleanSmsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}