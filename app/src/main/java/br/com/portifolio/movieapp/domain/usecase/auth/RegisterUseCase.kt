package br.com.portifolio.movieapp.domain.usecase.auth

import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication

class RegisterUseCase(
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend fun invoke(email: String, password: String) {
        firebaseAuthentication.register(email, password)
    }
}