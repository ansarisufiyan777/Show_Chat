package info.infiniteloops.discuss;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;
import info.infiniteloops.discuss.chat.data.StaticConfig;
import info.infiniteloops.discuss.chat.ui.ChatActivity;
import info.infiniteloops.discuss.model.Movie;
import info.infiniteloops.discuss.model.TV;

/**
 * Created by dell on 8/19/2017.
 */

public class Utilities {
    public static String TMDBAPI = "?api_key=75a3a95754907082faca941b1cfca50a";
    public static String TMDB_BASE_URL = "https://api.themoviedb.org/3";
    public static String POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static String POSTER_IMAGE_SIZE = "w780";

    public static String getTMDBMostPopularMovies(){
        return  TMDB_BASE_URL+"/discover/movie?sort_by=popularity.desc"+TMDBAPI;
    }
    public static String getTMDBImageURL(String imageID){
        return "https://image.tmdb.org/t/p/w300_and_h450_bestv2"+imageID;
    }
    public static String getTMDBSearchURL(String searchQuery,int page){
        return "http://api.themoviedb.org/3/search/movie?api_key=75a3a95754907082faca941b1cfca50a&" +
                "query="+searchQuery+
                "&page="+page;
    }
    public static DatabaseReference getFirebaseDBReference(){
        return FirebaseDatabase.getInstance().getReference().child("Movie-Talk");
    }

    public static String NAV_BG = "https://firebasestorage.googleapis.com/v0/b/have-fun-fa874.appspot.com/o/movie_talk%2Fnav-bg.jpg?alt=media&token=1f335273-d5bc-4522-83e8-412a5b5cfc94";
    /*Movie Chat*/
    public void checkGroupAvailablityAndOpenChat(final Movie movie, final Context context){
        Utilities.getFirebaseDBReference().child("movies").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(String.valueOf(movie.getId()))) {
                    addMember(movie,context);
                }else{
                    createMovieGroup(movie,context);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    private void createMovieGroup(final Movie movie, final Context context) {
        Utilities.getFirebaseDBReference().child("movies/" + movie.getId()+"/groupInfo").setValue(movie).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //addRoomForUser(idGroup, 0);
                addMember(movie,context);

            }
        });
    }
    private void addMember(final Movie movie, final Context context){
        Utilities.getFirebaseDBReference().child("movies/" + movie.getId()+"/member/"+ StaticConfig.UID).setValue(StaticConfig.UID).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //addRoomForUser(idGroup, 0);
                openChat(movie,context);
            }
        });
    }
    private void openChat(final Movie movie, final Context context){
        Utilities.getFirebaseDBReference().child("movies/"+movie.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    HashMap mapGroup = (HashMap) dataSnapshot.getValue();
                    HashMap member= (HashMap) mapGroup.get("member");
                    Iterator iterator = member.keySet().iterator();
                    HashMap mapGroupInfo = (HashMap) mapGroup.get("groupInfo");
                    ArrayList<CharSequence> idGroup = new ArrayList<CharSequence>();
                    while (iterator.hasNext()){
                        String idMember =  member.get(iterator.next().toString()).toString();
                        idGroup.add(idMember);
                    }
                    String roomID = String.valueOf(movie.getId());
                    String movieName = movie.getOriginalTitle();

                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra(StaticConfig.INTENT_KEY_CHAT_FRIEND, movieName);
                    intent.putCharSequenceArrayListExtra(StaticConfig.INTENT_KEY_CHAT_ID, idGroup);
                    intent.putExtra(StaticConfig.INTENT_KEY_CHAT_ROOM_ID, roomID);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /*TV Chat*/
    public void checkGroupAvailablityAndOpenChat(final TV tv, final Context context){
        //smoothProgressBar.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(context).interpolator(new AccelerateInterpolator()).build());

        //smoothProgressBar.setVisibility(View.VISIBLE);

        Utilities.getFirebaseDBReference().child("tvs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(String.valueOf(tv.getId()))) {
                    addMember(tv,context);
                }else{
                    createMovieGroup(tv,context);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    private void createMovieGroup(final TV tv, final Context context) {
        Utilities.getFirebaseDBReference().child("tvs/" + tv.getId()+"/groupInfo").setValue(tv).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //addRoomForUser(idGroup, 0);
                addMember(tv,context);

            }
        });
    }
    private void addMember(final TV tv, final Context context){
        Utilities.getFirebaseDBReference().child("tvs/" + tv.getId()+"/member/"+ StaticConfig.UID).setValue(StaticConfig.UID).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //addRoomForUser(idGroup, 0);
                openChat(tv,context);
            }
        });
    }
    private void openChat(final TV tv, final Context context){
        Utilities.getFirebaseDBReference().child("tvs/"+tv.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    HashMap mapGroup = (HashMap) dataSnapshot.getValue();
                    HashMap member= (HashMap) mapGroup.get("member");
                    Iterator iterator = member.keySet().iterator();
                    HashMap mapGroupInfo = (HashMap) mapGroup.get("groupInfo");
                    ArrayList<CharSequence> idGroup = new ArrayList<CharSequence>();
                    while (iterator.hasNext()){
                        String idMember =  member.get(iterator.next().toString()).toString();
                        idGroup.add(idMember);
                    }
                    String roomID = String.valueOf(tv.getId());
                    String movieName = tv.getOriginalName();

                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra(StaticConfig.INTENT_KEY_CHAT_FRIEND, movieName);
                    intent.putCharSequenceArrayListExtra(StaticConfig.INTENT_KEY_CHAT_ID, idGroup);
                    intent.putExtra(StaticConfig.INTENT_KEY_CHAT_ROOM_ID, roomID);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void subscribedMovie(final Context context, Movie movie){
        Utilities.getFirebaseDBReference().child("user/"+ StaticConfig.UID+"/subscribed/movies/"+movie.getId()).setValue(movie.getId()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context,"Subscribed Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void unSubscribedMovie(final Context context, Movie movie){
        Utilities.getFirebaseDBReference().child("user/"+ StaticConfig.UID+"/subscribed/movies")
                .child(String.valueOf(movie.getId()))
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context,"UnSubscribed Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void subscribedTV(final Context context, TV tv){
        Utilities.getFirebaseDBReference().child("user/"+ StaticConfig.UID+"/subscribed/tvs/"+tv.getId()).setValue(tv.getId()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context,"Subscribed Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void unSubscribedTV(final Context context, TV tv){
        Utilities.getFirebaseDBReference().child("user/"+ StaticConfig.UID+"/subscribed/tvs/")
                .child(String.valueOf(tv.getId()))
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context,"UnSubscribed Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }
}
