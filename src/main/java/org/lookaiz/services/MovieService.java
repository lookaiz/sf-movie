package org.lookaiz.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import org.lookaiz.entities.Movie;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class MovieService implements IMovieService {

    @ConfigProperty(name = "movies.file")
    private String moviesFilename;

    @ConfigProperty(name = "nb.movies.max")
    Integer nbMoviesMax;

    private List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> getAllMovies() {
        if (movies.isEmpty()) {
            movies = readMoviesFromJSON(moviesFilename);
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesContainingTitle(String title) {
        return this.getAllMovies().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .limit(nbMoviesMax)
                .sorted(Comparator.comparingInt(Movie::getReleaseYear))
                .collect(Collectors.toList());
    }


    private List<Movie> readMoviesFromJSON(String filename) {
        System.out.println("Start loading movies...");
        Instant start = Instant.now();
        List<Movie> tmpMovies = new ArrayList<>();
        try {
            String jsonString = new String ( Files.readAllBytes( Paths.get(getClass().getClassLoader().getResource(filename).toURI()) ));
            JSONArray data = new JSONObject(jsonString).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                tmpMovies.add(createMovie(i, data.getJSONArray(i)));
            }

            movies = normalize(tmpMovies);
        }
        catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        long timeElapsed = Duration.between(start, Instant.now()).toMillis();
        System.out.println("Movies loading time : " + timeElapsed + " ms");

        return movies;
    }

    // Factory
    private static Movie createMovie(Integer _id, JSONArray array) {
        int idx = 8; // offset
        String name = array.optString(idx++);
        Integer releaseYear = array.optInt(idx++);
        String locations = array.optString(idx++);
        String funFacts = array.optString(idx++);
        String productionCompany = array.optString(idx++);
        String distributor = array.optString(idx++);
        String director = array.optString(idx++);
        String writer = array.optString(idx++);
        String actor1 = array.optString(idx++);
        String actor2 = array.optString(idx++);
        String actor3 = array.optString(idx);

        return new Movie(_id, name, releaseYear, locations, funFacts, productionCompany, distributor, director, writer, actor1, actor2, actor3);
    }

    private List<Movie> normalize(List<Movie> movies) {
        // Group movies by key
        Map<String, List<Movie>> map = movies.stream()
                .collect(Collectors.groupingBy(this::getGroupingByKey, Collectors.mapping((Movie p) -> p, Collectors.toList())));

        // Only one movie by grouping key
        // Group locations and fun facts of same movie
        List<Movie> movieList = new ArrayList<>();
        for (var entry : map.entrySet()) {
            var m = entry.getValue().get(0);

            // locations grouping
            final var locations = entry.getValue().stream()
                    .map(Movie::getLocations)
                    .distinct()
                    .collect(Collectors.joining(System.lineSeparator()));
            m.setLocations(locations);

            // fun-facts grouping
            final var funFacts = entry.getValue().stream()
                    .map(Movie::getFunFacts)
                    .distinct()
                    .collect(Collectors.joining(System.lineSeparator()));
            m.setFunFacts(funFacts);

            // Poster URL
            m.setPosterUrl(ImageUtils.getOMDBImageUrl(m.getTitle(), m.getReleaseYear()));

            movieList.add(m);
        }

        return movieList;
    }

    private String getGroupingByKey(Movie m) {
        // Movie having same name and release year represents same movie
        return m.getTitle() + "-" + m.getReleaseYear();
    }

}
