package info.infiniteloops.discuss.model;

import com.google.gson.annotations.SerializedName;

import info.infiniteloops.discuss.service.BaseResults;

import java.util.List;

public class MovieResults extends BaseResults{
    @SerializedName("results")
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
