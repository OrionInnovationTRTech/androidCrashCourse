package com.orioninc.techclub.acchelloworld

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.orioninc.techclub.acchelloworld.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var userRepository: UserRepository? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userRepository = UserRepository(requireContext())
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            userRepository?.logout()

            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.welcomeMessageSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            val editor = sharedPreferences?.edit()

            if (isChecked) {
                binding.welcomeMessageText.visibility = View.VISIBLE
                editor?.putBoolean(KEY_WELCOME_MESSAGE_VISIBILITY, true)
            } else {
                binding.welcomeMessageText.visibility = View.GONE
                editor?.putBoolean(KEY_WELCOME_MESSAGE_VISIBILITY, false)
            }

            editor?.apply()
        }

        initializeWelcomeMessageComponent()

        return binding.root
    }

    private fun initializeWelcomeMessageComponent() {
        val isVisible = sharedPreferences?.getBoolean(KEY_WELCOME_MESSAGE_VISIBILITY, DEFAULT_WELCOME_MESSAGE_VISIBILITY)

        if (isVisible == true) {
            binding.welcomeMessageText.visibility = View.VISIBLE
            binding.welcomeMessageSwitch.isChecked = true
        } else {
            binding.welcomeMessageText.visibility = View.GONE
            binding.welcomeMessageSwitch.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}