package com.spyker3d.bininfo.history.presentation

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.spyker3d.bininfo.R
import com.spyker3d.bininfo.carddetails.presentation.CardDetailsFragment
import com.spyker3d.bininfo.databinding.FragmentHistoryBinding
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val CLICK_DEBOUNCE_DELAY = 200L

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModel<HistoryViewModel>()
    private var lastTimeClicked: Long = 0L

    private var adapter = CardInfoAdapter(emptyList(), ::setItemOnClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.searchRecyclerView.adapter = adapter

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setItemOnClickListener(cardInfo: CardInfo) {
        val moveToDetailsFragment = clickDebounce()
        if (moveToDetailsFragment) {
            findNavController().navigate(
                R.id.action_historyFragment_to_cardDetailsFragment,
                CardDetailsFragment.createArgs(cardInfo.binNumber)
            )
        }
    }

    private fun renderState(state: HistoryState) {
        binding.emtyHistoryMessage.isVisible = state is HistoryState.Empty
        if (state is HistoryState.Content) adapter.updateList(state.listOfHistoryRequests)
    }

    private fun clickDebounce(): Boolean {
        val currentTime = SystemClock.uptimeMillis()
        if (currentTime - lastTimeClicked < CLICK_DEBOUNCE_DELAY) return false

        lastTimeClicked = currentTime
        return true
    }

}