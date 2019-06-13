package com.popularapi.model.email;

import com.popularapi.model.common.Responses;

import java.util.List;

public class EmailResponse extends Responses {

    private List<EmailResult> results = null;

    public List<EmailResult> getResults() {
        return results;
    }

    public void setResults(List<EmailResult> results) {
        this.results = results;
    }

}
