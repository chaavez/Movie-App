package br.com.portifolio.movieapp.data.mapper

import br.com.portifolio.movieapp.data.model.GenreResponse
import br.com.portifolio.movieapp.data.model.MovieResponse
import br.com.portifolio.movieapp.domain.model.Genre
import br.com.portifolio.movieapp.domain.model.Movie

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult,
        backdropPath,
        genreIds,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}