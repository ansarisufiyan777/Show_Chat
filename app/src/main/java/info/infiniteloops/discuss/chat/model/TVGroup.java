package info.infiniteloops.discuss.chat.model;

import java.util.List;

/**
 * Created by dell on 9/16/2017.
 */

public class TVGroup {

    /**
     * backdropPath : /iU2cObod4GoHAcgrc5AtfwIVizL.jpg
     * episodeRunTime : [43,45,67,42,44]
     * firstAirDate : 2011-06-23
     * genres : [{"id":"18","name":"Drama"}]
     * homepage : http://www.usanetwork.com/suits
     * id : 37680
     * inProduction : true
     * languages : ["en"]
     * lastAirDate : 2017-09-20
     * name : Suits
     * networks : [{"id":"30","name":"USA Network"}]
     * numberOfEpisodes : 114
     * numberOfSeasons : 7
     * originCountry : ["US"]
     * originalLanguage : en
     * originalName : Suits
     * overview : While running from a drug deal gone bad, Mike Ross, a brilliant young college-dropout, slips into a job interview with one of New York City's best legal closers, Harvey Specter. Tired of cookie-cutter law school grads, Harvey takes a gamble by hiring Mike on the spot after he recognizes his raw talent and photographic memory. Mike and Harvey are a winning team. Even though Mike is a genius, he still has a lot to learn about law. And while Harvey may seem like an emotionless, cold-blooded shark, Mike's sympathy and concern for their cases and clients will help remind Harvey why he went into law in the first place. Mike's other allies in the office include the firm's best paralegal Rachel and Harvey's no-nonsense assistant Donna to help him serve justice. Proving to be an irrepressible duo and invaluable to the practice, Mike and Harvey must keep their secret from everyone including managing partner Jessica and Harvey's arch nemesis Louis, who seems intent on making Mike's life as difficult as possible.
     * popularity : 46.453729
     * posterPath : /i6Iu6pTzfL6iRWhXuYkNs8cPdJF.jpg
     * seasons : [{"airDate":"2012-06-07","id":"49019","posterPath":"/agyP4DQE9Ny8kZDc3hvaVoTxYgK.jpg","seasonNumber":0},{"airDate":"2011-06-23","id":"49020","posterPath":"/tPocb9DO6yrc4jI1JznPAnpU9yj.jpg","seasonNumber":1},{"airDate":"2012-06-14","id":"49021","posterPath":"/tX555XUvxQr2GOtvoO9DbWjOHi3.jpg","seasonNumber":2},{"airDate":"2013-07-16","id":"49022","posterPath":"/Ej4xWbtPWg8lhsNDzmWi4Qj8lW.jpg","seasonNumber":3},{"airDate":"2014-06-11","id":"61103","posterPath":"/7RSiqRgX2TGFdCXMzkNUxWmpirS.jpg","seasonNumber":4},{"airDate":"2015-06-23","id":"67345","posterPath":"/1RBn11qOQ69ogHElDkVIxFaX3Tz.jpg","seasonNumber":5},{"airDate":"2016-07-13","id":"77763","posterPath":"/sabhaQb6J5n7txuw6ruVrlZZrEq.jpg","seasonNumber":6},{"airDate":"2017-07-12","id":"90223","posterPath":"/7PxjFRbpOxdgSvsdHBGqckvTlT8.jpg","seasonNumber":7}]
     * status : Returning Series
     * voteAverage : 7.400000095367432
     * voteCount : 502
     */

    public String backdropPath;
    public String firstAirDate;
    public String homepage;
    public String id;
    public boolean inProduction;
    public String lastAirDate;
    public String name;
    public int numberOfEpisodes;
    public int numberOfSeasons;
    public String originalLanguage;
    public String originalName;
    public String overview;
    public double popularity;
    public String posterPath;
    public String status;
    public double voteAverage;
    public int voteCount;
    public List<Integer> episodeRunTime;
    public List<GenresBean> genres;
    public List<String> languages;
    public List<NetworksBean> networks;
    public List<String> originCountry;
    public List<SeasonsBean> seasons;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<NetworksBean> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworksBean> networks) {
        this.networks = networks;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<SeasonsBean> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsBean> seasons) {
        this.seasons = seasons;
    }

    public static class GenresBean {
        /**
         * id : 18
         * name : Drama
         */

        public String id;
        public String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class NetworksBean {
        /**
         * id : 30
         * name : USA Network
         */

        public String id;
        public String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SeasonsBean {
        /**
         * airDate : 2012-06-07
         * id : 49019
         * posterPath : /agyP4DQE9Ny8kZDc3hvaVoTxYgK.jpg
         * seasonNumber : 0
         */

        public String airDate;
        public String id;
        public String posterPath;
        public int seasonNumber;

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public int getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(int seasonNumber) {
            this.seasonNumber = seasonNumber;
        }
    }
}
