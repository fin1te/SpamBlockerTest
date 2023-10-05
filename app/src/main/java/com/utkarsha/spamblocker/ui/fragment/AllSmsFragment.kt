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
import com.utkarsha.spamblocker.databinding.FragmentAllSmsBinding
import com.utkarsha.spamblocker.repository.SmsRepo

class AllSmsFragment : Fragment() {

    private var _binding: FragmentAllSmsBinding? = null
    private val binding get() = _binding!!
    private lateinit var allSmsAdapter: SmsAdapter
    private lateinit var allSmsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentAllSmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        allSmsRecyclerView = binding.allSmsRecyclerView
        allSmsRecyclerView.setHasFixedSize(true)
        allSmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        allSmsAdapter = SmsAdapter(SmsRepo.getMockSms())
        allSmsRecyclerView.adapter = allSmsAdapter
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}