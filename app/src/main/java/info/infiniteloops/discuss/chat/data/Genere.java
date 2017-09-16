package info.infiniteloops.discuss.chat.data;

/**
 * Created by dell on 8/20/2017.
 */

public class Genere {
    public static String getGenereName(int id){
        switch (id){
            case 28:
                return "Action";
            case 12:
                return "Adventure";
            case 16:
                return "Animation";
            case 35:
                return "Comedy";
            case 80:
                return "Crime";
            case 99:
                return "Documentary";
            case 18:
                return "Drama";
            case 10751:
                return "Family";
            case 14:
                return "Fantasy";
            case 36:
                return "History";
            case 27:
                return "Horror";
            case 10402:
                return "Music";
            case 9648:
                return "Mystery";
            case 10749:
                return "Romance";
            case 878:
                return "Science Fiction";
            case 10770:
                return "TV Movie";
            case 53:
                return "Thriller";
            case 10752:
                return "War";
            case 37:
                return "Western";
            default:
                return "null";
        }
    }
    public static int getGenereID(String name){
        switch (name){
            case "Action":
                return 28;
            case "Adventure":
                return 12;
            case "Animation":
                return 16;
            case "Comedy":
                return 35;
            case "Crime":
                return 80;
            case "Documentary":
                return 99;
            case "Darama":
                return 18;
            case "Family":
                return 1051;
            case "Fantasy":
                return 14;
            case "History":
                return 36;
            case "Horror":
                return 27;
            case "Music":
                return 10402;
            case "Mystery":
                return 9648;
            case "Romance":
                return 10749;
            case "Science Fiction":
                return 878;
            case "TV Movie":
                return 10770;
            case "Thriller":
                return 53;
            case "War":
                return 10752;
            case "Western":
                return 37;
            default:
                return 0;
        }
    }

}
