package com.orioninc.techclub.acchelloworld

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.orioninc.techclub.acchelloworld.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            login(username, password)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(username: String, password: String) {
        GlobalScope.launch {
            launch(Dispatchers.IO) {
                val body = LoginBody(username, password)

                val response = RetrofitClient.userService.login(body)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    } else {
                        Toast.makeText(context, "Login is failed!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}