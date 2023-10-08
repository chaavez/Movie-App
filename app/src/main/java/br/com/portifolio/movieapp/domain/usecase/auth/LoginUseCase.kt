package br.com.portifolio.movieapp.domain.usecase.auth

import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication

class LoginUseCase(
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend fun invoke(email: String, password: String) {
        firebaseAuthentication.login(email, password)
    }
}