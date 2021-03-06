package info.infiniteloops.discuss.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.infiniteloops.discuss.R;
import info.infiniteloops.discuss.service.ServiceGenerator;
import info.infiniteloops.discuss.adapter.MoviesAdapter;
import info.infiniteloops.discuss.model.Movie;
import info.infiniteloops.discuss.model.MovieResults;
import info.infiniteloops.discuss.service.DiscoverService;
import info.infiniteloops.discuss.service.MovieService;
import info.infiniteloops.discuss.ui.ItemOffsetDecoration;
import info.infiniteloops.discuss.util.PrefUtils;
import info.infiniteloops.discuss.util.StringUtils;
import info.infiniteloops.discuss.view.CustomErrorView;
import info.infiniteloops.discuss.view.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private static final String TAG = MoviesFragment.class.getSimpleName();

    private int mType;
    private static final String GET_MOVIES_TYPE = "info.infiniteloops.discuss.movie.get_movies_type";
    public static final int POPULAR = 0;
    public static final int TOP_RATED = 1;
    public static final int UPCOMING = 2;
    public static final int NOW_PLAYING = 3;

    private MoviesAdapter mAdapter;
    private List<Movie> mMovies;
    private int mPage = 1;
    private String mLang;
    private GridLayoutManager gridLayoutManager;

    private Unbinder unbinder;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progressBar) CircularProgressBar progressBar;
    @BindView(R.id.error) CustomErrorView mCustomErrorView;

    public MoviesFragment newInstance(int type) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putInt(GET_MOVIES_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(GET_MOVIES_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        unbinder = ButterKnife.bind(this, v);

        mLang = PrefUtils.getFormatLocale(getActivity());
        mMovies = new ArrayList<>();
        mAdapter = new MoviesAdapter(mMovies);

        int columns = 2;
        gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.movie_item_offset));

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                mPage = page;
                loadMovies();
            }
        });
        updateProgressBar(true);
        loadMovies();

        return v;
    }

    private void loadMovies() {
        switch (mType) {
            case POPULAR:
                loadPopular();
                break;
            case TOP_RATED:
                loadTopRated();
                break;
            case UPCOMING:
                loadUpcoming();
                break;
            case NOW_PLAYING:
                loadNowPlaying();
                break;
        }
    }

    private void loadPopular() {
        MovieService popularService = ServiceGenerator.createService(MovieService.class);
        Call<MovieResults> popularCall = popularService.popular(ServiceGenerator.API_KEY, mLang, mPage);
        popularCall.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getMovies();
                    addMovies(movies);
                } else {
                    Log.i("TAG", "Res: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                onLoadFailed(t);
            }
        });
    }

    private void loadTopRated() {
        MovieService topRatedService = ServiceGenerator.createService(MovieService.class);
        Call<MovieResults> topRatedCall = topRatedService.topRated(ServiceGenerator.API_KEY, mLang, mPage);
        topRatedCall.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getMovies();
                    addMovies(movies);
                } else {
                    Log.i("TAG", "Res: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                onLoadFailed(t);
            }
        });
    }

    private void loadUpcoming() {
        MovieService upcomingService = ServiceGenerator.createService(MovieService.class);
        Call<MovieResults> upcomingCall = upcomingService.upcoming(ServiceGenerator.API_KEY, mLang, mPage);
        upcomingCall.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getMovies();
                    addMovies(movies);
                } else {
                    Log.i("TAG", "Res: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                onLoadFailed(t);
            }
        });
    }

    private void loadNowPlaying() {
        DiscoverService nowPlayingService = ServiceGenerator.createService(DiscoverService.class);
        Call<MovieResults> nowPlayingCall = nowPlayingService.inTheaters(ServiceGenerator.API_KEY, mLang, mPage, "popularity.desc", StringUtils.inTheatersLte(), StringUtils.inTheatersGte());
        nowPlayingCall.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                List<Movie> movies = response.body().getMovies();
                addMovies(movies);
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                onLoadFailed(t);
            }
        });
    }

    private void addMovies(List<Movie> movies) {
        if (movies != null) {
            mMovies.addAll(movies);
            mAdapter.notifyDataSetChanged();
        }
        updateProgressBar(false);
    }

    private void updateProgressBar(boolean visibility) {
        if (progressBar != null) {
            progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
        }
    }

    private void onLoadFailed(Throwable t) {
        mCustomErrorView.setError(t);
        mCustomErrorView.setVisibility(View.VISIBLE);
        updateProgressBar(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
