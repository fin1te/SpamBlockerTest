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
import com.utkarsha.spamblocker.databinding.FragmentSpamSmsBinding
import com.utkarsha.spamblocker.repository.SmsRepo

class SpamSmsFragment : Fragment() {

    private var _binding: FragmentSpamSmsBinding? = null
    private val binding get() = _binding!!
    private lateinit var spamSmsAdapter: SmsAdapter
    private lateinit var spamSmsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentSpamSmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        spamSmsRecyclerView = binding.spamSmsRecyclerView
        spamSmsRecyclerView.setHasFixedSize(true)
        spamSmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        spamSmsAdapter = SmsAdapter(SmsRepo.getMockSms())
        spamSmsRecyclerView.adapter = spamSmsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}