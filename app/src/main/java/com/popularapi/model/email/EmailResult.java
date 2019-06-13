package com.popularapi.model.email;

import com.google.gson.annotations.SerializedName;
import com.popularapi.model.common.results.EmailAndShareResults;

public class EmailResult extends EmailAndShareResults {

    @SerializedName("email_count")
    private Integer emailCount;

    public Integer getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(Integer emailCount) {
        this.emailCount = emailCount;
    }

}
