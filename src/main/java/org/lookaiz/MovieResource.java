package org.lookaiz;

import org.lookaiz.entities.Movie;
import org.lookaiz.services.IMovieService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("movies")
public class MovieResource {

    @Inject
    IMovieService movieService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAllMovies() {
        return this.movieService.getAllMovies();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer countMovies() {
        return this.movieService.getAllMovies().size();
    }

    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovie(@PathParam("title") String title) {
        return this.movieService.getMoviesContainingTitle(title);
    }

//    @GET
//    @Path("/byDecade")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Map<Integer, Integer> getNbMoviesByDecade() {
//         TODO MMO : implement
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(1920, 35);
//        map.put(1930, 44);
//        map.put(1940, 123);
//        return map;
//    }

}
