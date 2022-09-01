package com.orioninc.techclub.acchelloworld.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.orioninc.techclub.acchelloworld.R
import com.orioninc.techclub.acchelloworld.data.repository.UserRepository


class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        subscribeUI()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.autoLogin()
    }

    private fun subscribeUI() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SplashViewModel.UIState.IDLE -> {
                    Log.d("SplashFragment", "UI state is IDLE.")
                }
                SplashViewModel.UIState.LOGGED_IN -> {
                    findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                }
                SplashViewModel.UIState.LOGIN_FAILED -> {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
                else -> {
                    Log.d("SplashFragment", "Unhandled UI state: $state")
                }
            }
        }
    }
}