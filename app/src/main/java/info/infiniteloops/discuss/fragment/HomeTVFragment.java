package info.infiniteloops.discuss.fragment;

import android.content.Context;
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
import info.infiniteloops.discuss.adapter.HomeTvAdapter;
import info.infiniteloops.discuss.model.TV;
import info.infiniteloops.discuss.model.TVResults;
import info.infiniteloops.discuss.service.DiscoverService;
import info.infiniteloops.discuss.ui.ItemOffsetDecoration;
import info.infiniteloops.discuss.util.PrefUtils;
import info.infiniteloops.discuss.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeTVFragment extends Fragment {

    private static final String TAG = HomeTVFragment.class.getSimpleName();

    @BindView(R.id.home_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) CircularProgressBar progressBar;

    private HomeTvAdapter mAdapter;
    private List<TV> mTVList;
    private int mPage = 1;
    private String mLang;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_recycler_view, container, false);
        ButterKnife.bind(this, v);
        mLang = PrefUtils.getFormatLocale(getActivity());

        mTVList = new ArrayList<>();
        mAdapter = new HomeTvAdapter(mTVList);


        int columns = 2;
        gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.movie_item_offset));

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.getLayoutManager().setAutoMeasureEnabled(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);

        updateProgressBar(true);
        DiscoverService service = ServiceGenerator.createService(DiscoverService.class);
        Call<TVResults> call = service.onTv(StringUtils.getDateOnTheAir(), StringUtils.getDateToday(), "popularity.desc", mLang, mPage, ServiceGenerator.API_KEY);
        call.enqueue(new Callback<TVResults>() {
            @Override
            public void onResponse(Call<TVResults> call, Response<TVResults> response) {
                if (response.isSuccessful()) {
                    List<TV> tvs = response.body().getTVs();
                    mTVList.clear();
                    if (tvs != null) {
                        for (int i = 0; i < 10; i++) {
                            TV tv = tvs.get(i);
                            mTVList.add(tv);
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    updateProgressBar(false);
                } else {
                    Log.i("TAG", "Res: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TVResults> call, Throwable t) {
                Log.i("TAG", "Error: " + t.getMessage());
                updateProgressBar(false);
            }
        });

        return v;
    }

    private void updateProgressBar(boolean visibility) {
        progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "HomeTVFragment onAttach");
    }
}
