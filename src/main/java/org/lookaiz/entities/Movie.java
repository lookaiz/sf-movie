package org.lookaiz.entities;

import java.util.Objects;

/**
 * Movie DTO
 */
public class Movie {

    //Title,Release Year,Locations,Fun Facts,Production Company,Distributor,Director,Writer,Actor 1,Actor 2,Actor 3

    // technical unique identifier
    private Integer _id;

    private String title;
    private Integer releaseYear;
    private String locations;
    private String funFacts;
    private String productionCompany;
    private String distributor;
    private String director;
    private String writer;
    private String actor1;
    private String actor2;
    private String actor3;

    private String posterUrl;

    public Movie() {
        super();
    }

    public Movie(Integer _id, String title, Integer releaseYear, String locations, String funFacts, String productionCompany, String distributor, String director, String writer, String actor1, String actor2, String actor3) {
        this._id = _id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.locations = locations;
        this.funFacts = funFacts;
        this.productionCompany = productionCompany;
        this.distributor = distributor;
        this.director = director;
        this.writer = writer;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    public Integer getId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLocations() {
        return locations;
    }
    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getFunFacts() {
        return funFacts;
    }
    public void setFunFacts(String funFacts) {
        this.funFacts = funFacts;
    }

    public String getProductionCompany() {
        return productionCompany;
    }
    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getDistributor() {
        return distributor;
    }
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActor1() {
        return actor1;
    }
    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }
    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }
    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(releaseYear, movie.releaseYear) &&
                Objects.equals(locations, movie.locations) &&
                Objects.equals(funFacts, movie.funFacts) &&
                Objects.equals(productionCompany, movie.productionCompany) &&
                Objects.equals(distributor, movie.distributor) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(writer, movie.writer) &&
                Objects.equals(actor1, movie.actor1) &&
                Objects.equals(actor2, movie.actor2) &&
                Objects.equals(actor3, movie.actor3);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, releaseYear, locations, funFacts, productionCompany, distributor, director, writer, actor1, actor2, actor3);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", locations='" + locations + '\'' +
                ", funFacts='" + funFacts + '\'' +
                ", productionCompany='" + productionCompany + '\'' +
                ", distributor='" + distributor + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actor1='" + actor1 + '\'' +
                ", actor2='" + actor2 + '\'' +
                ", actor3='" + actor3 + '\'' +
                '}';
    }
}
