package com.popularapi.model.share;

import com.google.gson.annotations.SerializedName;
import com.popularapi.model.common.results.EmailAndShareResults;

public class ShareResult extends EmailAndShareResults {

    @SerializedName("share_count")
    private int shareCount;

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

}
