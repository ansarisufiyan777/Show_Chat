package info.infiniteloops.discuss.chat.model;

/**
 * Created by dell on 9/16/2017.
 */

public class MovieGroup {

    /**
     * adult : false
     * backdropPath : /tcheoA2nPATCm2vvXw2hVQoaEFD.jpg
     * budget : 0
     * id : 346364
     * originalLanguage : en
     * originalTitle : It
     * overview : In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies and a monster that takes the shape of a clown called Pennywise.
     * popularity : 380.831557
     * posterPath : /9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg
     * releaseDate : 2017-09-05
     * revenue : 0
     * runtime : 0
     * title : It
     * video : false
     * voteAverage : 7.5
     * voteCount : 541
     */

    public boolean adult;
    public String backdropPath;
    public int budget;
    public String id;
    public String originalLanguage;
    public String originalTitle;
    public String overview;
    public double popularity;
    public String posterPath;
    public String releaseDate;
    public int revenue;
    public int runtime;
    public String title;
    public boolean video;
    public double voteAverage;
    public int voteCount;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
