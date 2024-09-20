package com.spyker3d.bininfo.search.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.spyker3d.bininfo.R
import com.spyker3d.bininfo.databinding.FragmentSearchBinding
import com.spyker3d.bininfo.search.domain.entities.BankDetails
import com.spyker3d.bininfo.search.domain.entities.CardDetails
import com.spyker3d.bininfo.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var latestUserInput = ""

        binding.inputEditText.doOnTextChanged { text, _, _, _ ->
            val formattedText = text.toString().replace(" ", "").chunked(4).joinToString(" ")
            if (formattedText != text.toString()) {
                binding.inputEditText.setText(formattedText)
                binding.inputEditText.setSelection(binding.inputEditText.length())
            }
            latestUserInput = text.toString().replace(" ", "")
        }

        binding.buttonSearch.setOnClickListener {
            hideKeyboard(binding.inputEditText)
            viewModel.processSearchRequest(latestUserInput)
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            render(it)
        }

        binding.url.setOnClickListener {
            viewModel.openUrl(binding.url.text.toString())
        }

        binding.phone.setOnClickListener {
            viewModel.dialNumber(binding.phone.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideKeyboard(view: View) {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun render(state: SearchState) {
        binding.buttonSearch.background =
            if (state is SearchState.Loading && this.isAdded) {
                AppCompatResources.getDrawable(
                    requireActivity(),
                    R.drawable.create_new_playlist_button_color_gone
                )
            } else {
                AppCompatResources.getDrawable(
                    requireActivity(),
                    R.drawable.create_new_playlist_button_color
                )
            }
        binding.progressCircular.isVisible = state is SearchState.Loading
        when (state) {
            SearchState.Loading -> clearCardInfo()

            is SearchState.Content -> {
                val cardDetails = state.cardInfo.cardDetails
                val bankDetails = state.cardInfo.bankDetails
                showCardInfo(cardDetails, bankDetails)
            }

            is SearchState.InternetConnectionError -> showSnackBar(state.errorMessage)

            is SearchState.NotFoundError -> showSnackBar(state.errorMessage)

            is SearchState.ServerError -> showSnackBar(state.errorMessage)

            is SearchState.SocketTimeOut -> showSnackBar(state.errorMessage)
        }

    }

    private fun showCardInfo(cardDetails: CardDetails, bankDetails: BankDetails) {
        binding.paymentNetworkInput.text = cardDetails.paymentNetwork
        binding.brandInput.text = cardDetails.type
        binding.lengthOfNumberInput.text = cardDetails.numberLength.toString()
        binding.luhnInput.text = if (cardDetails.luhn == true) {
            requireActivity().getString(R.string.yes)
        } else {
            requireActivity().getString(R.string.no)
        }
        binding.typeInput.text = cardDetails.type
        binding.prepaidInput.text = if (cardDetails.prepaid == true) {
            requireActivity().getString(R.string.yes)
        } else {
            requireActivity().getString(R.string.no)
        }
        binding.countryInput.text = cardDetails.countryName
        binding.latitudeInput.text = cardDetails.countryLatitude.toString()
        binding.longitudeInput.text = cardDetails.countryLongitude.toString()
        binding.bankInput.text = bankDetails.name
        binding.url.text = bankDetails.url
        binding.phone.text = bankDetails.phone
    }

    private fun clearCardInfo() {
        binding.paymentNetworkInput.text = null
        binding.brandInput.text = null
        binding.lengthOfNumberInput.text = null
        binding.luhnInput.text = null
        binding.typeInput.text = null
        binding.prepaidInput.text = null
        binding.countryInput.text = null
        binding.latitudeInput.text = null
        binding.longitudeInput.text = null
        binding.bankInput.text = null
        binding.url.text = null
        binding.phone.text = null
    }

}