package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.MovieRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new ApiException("Movie is not found with id: " + id,
                            HttpStatus.NOT_FOUND));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie existing = findById(id);

        existing.setName(movie.getName());
        existing.setDirectorName(movie.getDirectorName());
        existing.setRating(movie.getRating());
        existing.setReleaseDate(movie.getReleaseDate());
        existing.setActors(movie.getActors());

        return movieRepository.save(existing);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }
}
