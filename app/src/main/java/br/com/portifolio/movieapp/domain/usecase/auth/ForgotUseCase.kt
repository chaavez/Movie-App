package br.com.portifolio.movieapp.domain.usecase.auth

import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication
import javax.inject.Inject

class ForgotUseCase @Inject constructor(
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend fun invoke(email: String) {
        firebaseAuthentication.forgot(email)
    }
}