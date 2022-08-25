package com.orioninc.techclub.acchelloworld

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {

    private var userRepository: UserRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRepository = UserRepository(requireContext())

        userRepository?.autoLogin { isSuccess ->
            if (isSuccess)
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}