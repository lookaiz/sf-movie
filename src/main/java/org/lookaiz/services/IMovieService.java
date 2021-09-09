package org.lookaiz.services;

import org.lookaiz.entities.Movie;

import java.util.List;

public interface IMovieService {

    /**
     * Returns the list of all movies
     * @return : list of all movies
     */
    List<Movie> getAllMovies();

    /**
     * Returns the list of movies where title contains given char sequence.<br>
     * Movies are sorted by release year ascending.
     *
     * @param title
     * @return : list of movies containing title, sorted by release year ascending
     */
    List<Movie> getMoviesContainingTitle(String title);

}
