package com.example.coordinatechart.ui.start_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.coordinatechart.App
import com.example.coordinatechart.R
import com.example.coordinatechart.databinding.FragmentStartBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragment @Inject constructor() : Fragment() {

    private var binding: FragmentStartBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<StartFragmentViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as App).appComponent.injectStartFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding?.let {
            it.sentButton.setOnClickListener { _ ->
                val count = it.pointInput.text.toString()
                if (count.isNotEmpty()) {
                    try {
                        viewModel.onSentButtonClicked(count.toInt())
                    } catch (e: Exception) {
                        showToast(getString(R.string.start_screen_toast_error_input_not_int))
                    }
                } else {
                    showToast(getString(R.string.start_screen_toast_error_input_empty))
                }
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.coordinateState.collect {
                    when (it) {
                        is StartScreenState.Initial -> {
                            binding?.progressBar?.isVisible = false
                        }

                        is StartScreenState.Loading -> {
                            binding?.progressBar?.isVisible = true
                        }

                        is StartScreenState.Error -> {
                            binding?.progressBar?.isVisible = false
                            showToast(it.message)
                        }

                        is StartScreenState.Success -> {
                            binding?.progressBar?.isVisible = false
                            findNavController().navigate(R.id.resultFragment)
                        }
                    }
                }
            }
        }
    }

    private fun showToast(toastText: String) {
        Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()
    }

}