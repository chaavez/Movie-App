package br.com.portifolio.movieapp.features.auth.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import br.com.portifolio.movieapp.R
import br.com.portifolio.movieapp.databinding.FragmentRegisterBinding
import br.com.portifolio.movieapp.features.main.MainActivity
import br.com.portifolio.movieapp.utils.FirebaseHelper
import br.com.portifolio.movieapp.utils.StateView
import br.com.portifolio.movieapp.utils.hideKeyboard
import br.com.portifolio.movieapp.utils.initToolbar
import br.com.portifolio.movieapp.utils.isEmailValid
import br.com.portifolio.movieapp.utils.showSnackBar
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.registerButton.setOnClickListener {
            validateData()
        }

        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.progressLoading)
    }

    private fun validateData() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (email.isEmailValid()) {
            if (password.isNotEmpty()) {
                hideKeyboard()
                registerUser(email, password)
            } else {
                showSnackBar(
                    message = R.string.text_password_empty_register_fragment
                )
            }
        } else {
            showSnackBar(
                message = R.string.text_email_empty_register_fragment
            )
        }
    }

    private fun registerUser(email: String, password: String) {
        viewModel.register(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressLoading.isVisible = true
                }

                is StateView.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                    showSnackBar(
                        message = R.string.text_send_email_success_forgot_password_fragment
                    )
                }

                is StateView.Error -> {
                    binding.progressLoading.isVisible = false
                    showSnackBar(
                        message = FirebaseHelper.validError(error = stateView.message ?: "")
                    )
                }

                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}