package br.com.portifolio.movieapp.features.moviegenre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import br.com.portifolio.movieapp.databinding.FragmentMovieGenreBinding
import br.com.portifolio.movieapp.features.main.bottombar.home.adapter.MovieAdapter
import br.com.portifolio.movieapp.utils.StateView
import br.com.portifolio.movieapp.utils.initToolbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieGenreFragment : Fragment() {
    private var _binding: FragmentMovieGenreBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    private val args: MovieGenreFragmentArgs by navArgs()
    private val viewModel: MovieGenreViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initRecyclerView()
        getMoviesByGenre()
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(requireContext())

        with(binding.moviesRecyclerView) {
            setHasFixedSize(true)
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun getMoviesByGenre() {
        viewModel.getMoviesByGenre(args.genreId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressBar.isVisible = false
                    movieAdapter.submitList(stateView.data)
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}