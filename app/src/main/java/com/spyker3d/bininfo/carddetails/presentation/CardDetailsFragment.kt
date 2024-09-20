package com.spyker3d.bininfo.carddetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.spyker3d.bininfo.R
import com.spyker3d.bininfo.databinding.FragmentCardDetailsBinding
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CardDetailsFragment : Fragment() {
    private var _binding: FragmentCardDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CardDetailsViewModel by viewModel<CardDetailsViewModel> {
        parametersOf(requireArguments().getString(BIN_NUMBER))
    }

    lateinit var cardInfo: CardInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarHistory.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            cardInfo = it
            render(it)
        }

        binding.buttonDelete.setOnClickListener {
            parentFragmentManager.popBackStack()
            viewModel.deleteCardInfoFromHistory(cardInfo.binNumber)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }

        binding.url.setOnClickListener {
            viewModel.openUrl(binding.url.text.toString())
        }

        binding.phone.setOnClickListener {
            viewModel.dialNumber(binding.phone.text.toString())
        }
    }

    private fun render(cardInfo: CardInfo) {
        binding.paymentNetworkInput.text = cardInfo.cardDetails.paymentNetwork
        binding.brandInput.text = cardInfo.cardDetails.type
        binding.lengthOfNumberInput.text = cardInfo.cardDetails.numberLength.toString()
        binding.luhnInput.text = if (cardInfo.cardDetails.luhn == true) {
            requireActivity().getString(R.string.yes)
        } else {
            requireActivity().getString(R.string.no)
        }
        binding.typeInput.text = cardInfo.cardDetails.type
        binding.prepaidInput.text = if (cardInfo.cardDetails.prepaid == true) {
            requireActivity().getString(R.string.yes)
        } else {
            requireActivity().getString(R.string.no)
        }
        binding.countryInput.text = cardInfo.cardDetails.countryName
        binding.latitudeInput.text = cardInfo.cardDetails.countryLatitude.toString()
        binding.longitudeInput.text = cardInfo.cardDetails.countryLongitude.toString()
        binding.bankInput.text = cardInfo.bankDetails.name
        binding.url.text = cardInfo.bankDetails.url
        binding.phone.text = cardInfo.bankDetails.phone
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BIN_NUMBER = "bin_number"
        fun createArgs(binNumber: String): Bundle = bundleOf(BIN_NUMBER to binNumber)
    }
}