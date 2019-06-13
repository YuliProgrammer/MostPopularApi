package com.popularapi.model.share;

import com.popularapi.model.common.Responses;

import java.util.List;

public class ShareResponse extends Responses {

    private List<ShareResult> results = null;

    public List<ShareResult> getResults() {
        return results;
    }

    public void setResults(List<ShareResult> results) {
        this.results = results;
    }

}
