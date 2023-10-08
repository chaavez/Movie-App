package br.com.portifolio.movieapp.domain.usecase.auth

import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend fun invoke(email: String, password: String) {
        firebaseAuthentication.login(email, password)
    }
}