package br.com.portifolio.movieapp.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import br.com.portifolio.movieapp.databinding.FragmentHomeBinding
import br.com.portifolio.movieapp.features.home.adapter.GenreMovieAdapter
import br.com.portifolio.movieapp.features.model.GenreFeature
import br.com.portifolio.movieapp.utils.StateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var genreMovieAdapter: GenreMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getGenres()
    }

    private fun getGenres() {
        viewModel.getGenres().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    val genres = stateView.data ?: emptyList()
                    genreMovieAdapter.submitList(genres)
                    getMoviesByGenre(genres)
                }

                is StateView.Error -> {

                }
            }
        }
    }

    private fun getMoviesByGenre(genres: List<GenreFeature>) {
        val genreMutableList = genres.toMutableList()

        genreMutableList.forEachIndexed { index, genre ->
            viewModel.getMoviesByGenre(genre.id).observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }

                    is StateView.Success -> {
                        genreMutableList[index] = genre.copy(movies = stateView.data?.take(5))
                        lifecycleScope.launch {
                            delay(1000)
                            genreMovieAdapter.submitList(genreMutableList)
                        }
                    }

                    is StateView.Error -> {

                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        genreMovieAdapter = GenreMovieAdapter()

        with(binding.genreRecyclerView) {
            setHasFixedSize(true)
            adapter = genreMovieAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}