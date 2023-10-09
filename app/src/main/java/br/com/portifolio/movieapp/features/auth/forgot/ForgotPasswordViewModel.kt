package br.com.portifolio.movieapp.features.auth.forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.portifolio.movieapp.domain.usecase.auth.ForgotPasswordUseCase
import br.com.portifolio.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    fun forgotPassword(email: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            forgotPasswordUseCase.invoke(email)
            emit(StateView.Success(Unit))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }
}