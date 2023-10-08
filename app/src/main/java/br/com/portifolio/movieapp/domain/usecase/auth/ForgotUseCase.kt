package br.com.portifolio.movieapp.domain.usecase.auth

import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication

class ForgotUseCase(
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend fun invoke(email: String) {
        firebaseAuthentication.forgot(email)
    }
}