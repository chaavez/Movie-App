package br.com.portifolio.movieapp.features.auth.forgot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import br.com.portifolio.movieapp.R
import br.com.portifolio.movieapp.databinding.FragmentForgotPasswordBinding
import br.com.portifolio.movieapp.utils.FirebaseHelper
import br.com.portifolio.movieapp.utils.StateView
import br.com.portifolio.movieapp.utils.hideKeyboard
import br.com.portifolio.movieapp.utils.initToolbar
import br.com.portifolio.movieapp.utils.isEmailValid
import br.com.portifolio.movieapp.utils.showSnackBar
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners() {
        binding.textButtonForgotButton.setOnClickListener {
            validateData()
        }

        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.progressLoading)
    }

    private fun validateData() {
        val email = binding.newPasswordEditText.text.toString()
        if (email.isEmailValid()) {
            hideKeyboard()
            forgotPassword(email)
        } else {
            showSnackBar(
                message = R.string.text_email_empty_forgot_password_fragment
            )
        }
    }

    private fun forgotPassword(email: String) {
        viewModel.forgotPassword(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressLoading.isVisible = true
                }

                is StateView.Success -> {
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