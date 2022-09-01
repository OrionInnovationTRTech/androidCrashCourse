package com.orioninc.techclub.acchelloworld.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.orioninc.techclub.acchelloworld.R
import com.orioninc.techclub.acchelloworld.data.repository.UserRepository
import com.orioninc.techclub.acchelloworld.databinding.FragmentLoginBinding
import com.orioninc.techclub.acchelloworld.domain.LoginUseCase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    private var userRepository: UserRepository? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userRepository = UserRepository(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        initializeUI()
        subscribeUI()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeUI() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            viewModel.login(username, password)
        }
    }

    private fun subscribeUI() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                LoginViewModel.UIState.IDLE -> {
                    enableLoginComponents()
                }
                LoginViewModel.UIState.LOGIN_IN_PROGRESS -> {
                    disableLoginComponents()
                }
                LoginViewModel.UIState.LOGGED_IN -> {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
                LoginViewModel.UIState.LOGIN_FAILED -> {
                    Toast.makeText(context, "Login is failed!", Toast.LENGTH_LONG).show()

                    enableLoginComponents()
                }
                else -> {
                    Log.d("LoginFragment", "Unhandled UI state: $state")
                }
            }
        }
    }

    private fun enableLoginComponents() {
        binding.loginButton.isEnabled = true
        binding.usernameEdittext.isEnabled = true
        binding.passwordEdittext.isEnabled = true
    }

    private fun disableLoginComponents() {
        binding.loginButton.isEnabled = false
        binding.usernameEdittext.isEnabled = false
        binding.passwordEdittext.isEnabled = false
    }
}