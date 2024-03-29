package com.popularapi.model.common;

import com.google.gson.annotations.SerializedName;

public abstract class Responses {

    private String status;
    private String copyright;
    @SerializedName("num_results")
    private int numResults;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }
}
