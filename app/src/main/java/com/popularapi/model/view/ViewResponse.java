package com.popularapi.model.view;

import com.popularapi.model.common.Responses;

import java.util.List;

public class ViewResponse extends Responses {

    private List<ViewResult> results = null;

    public List<ViewResult> getResults() {
        return results;
    }

    public void setResults(List<ViewResult> results) {
        this.results = results;
    }

}
