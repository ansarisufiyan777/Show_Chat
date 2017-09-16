package info.infiniteloops.discuss.model;

import com.google.gson.annotations.SerializedName;

import info.infiniteloops.discuss.service.BaseResults;

import java.util.List;

public class TVResults extends BaseResults{
    @SerializedName("results")
    private List<TV> mTVs;

    public List<TV> getTVs() {
        return mTVs;
    }

    public void setTVs(List<TV> TVs) {
        mTVs = TVs;
    }
}
