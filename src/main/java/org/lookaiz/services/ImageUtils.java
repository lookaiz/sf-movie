package org.lookaiz.services;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public final class ImageUtils {

    // http://www.omdbapi.com/
    private static final String OMDB_API_KEY = "21ef9ee4";
    private static final String OMDB_BASE_URL = "http://www.omdbapi.com/";
    private static final String POSTER_KEY = "Poster";

    public static String getOMDBImageUrl(String title, int releaseYear) {
        try {
            final String url = OMDB_BASE_URL +  "?apikey=" + OMDB_API_KEY + "&t=" + title.replaceAll(" ", "+") + "&y=" + releaseYear;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();
            if (isNotBlank(body)) {
                return new JSONObject(body).getString(POSTER_KEY);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

//    private static String getIMDBStaticImages(String title, int releaseYear) {
//        if (title.equalsIgnoreCase("hulk") && releaseYear == 2003) {
//            return "https://imdb-api.com/images/original/MV5BMzQwZDg1MGEtN2E5My00ZDJlLWI4MzItM2U2MjJhYzlkNmEzXkEyXkFqcGdeQXVyNDAxNjkxNjQ@._V1_Ratio0.6800_AL_.jpg";
//        }
//        else if (title.equalsIgnoreCase("ant-man") && releaseYear == 2015) {
//            return "https://m.media-amazon.com/images/M/MV5BMjM2NTQ5Mzc2M15BMl5BanBnXkFtZTgwNTcxMDI2NTE@._V1_SX300.jpg";
//        }
//        else if (title.equalsIgnoreCase("bullitt") && releaseYear == 1968) {
//            return "https://m.media-amazon.com/images/M/MV5BNWYxNzIxOTEtZWQyNS00OWY3LTgwNmMtMTI1MjI1MTE5OTZkXkEyXkFqcGdeQXVyNjUwMzI2NzU@._V1_SX300.jpg";
//        }
//        return "";
//    }

//    private static String getIMDBImageUrl(String title, int releaseYear) {
//        try {
//            final String IMDB_API_KEY = "k_keumyts6";
//
//            final String SEARCH_BASE_URL = "https://imdb-api.com/en/API/SearchMovie";
////            String url = String.format("%s/%s/%s\\%20%s", SEARCH_BASE_URL, IMDB_API_KEY, movie.getTitle(), movie.getReleaseYear());
////            String url = "https://imdb-api.com/en/API/SearchMovie/k_keumyts6/Hulk%202003";
////            String url = "https://imdb-api.com/en/API/SearchMovie/pk_z15kcffe7tpltpp4d/Hulk%202003";
//
//            String url = SEARCH_BASE_URL + "/" +
//                    IMDB_API_KEY + "/" +
//                    title.replaceAll(" ", "%20") + "%20" +
//                    releaseYear;
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(url))
//                    .GET()
//                    .build();
//
//            HttpClient client = HttpClient.newBuilder().build();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
////            System.out.println("=> IMDB URL = " + url);
//
//            return new JSONObject(response.body()).getJSONArray("results").getJSONObject(0).getString("image");
//        }
//        catch (Throwable t) {
//            t.printStackTrace();
//        }
//
//        return null;
//    }

}
