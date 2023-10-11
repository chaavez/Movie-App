package br.com.portifolio.movieapp.features.auth.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.portifolio.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}