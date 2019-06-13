package com.popularapi.model.common.results;

import com.google.gson.annotations.SerializedName;

public abstract class EmailAndShareResults extends Results {

    private String subsection;
    @SerializedName("count_type")
    private String countType;
    @SerializedName("eta_id")
    private Integer etaId;
    private String nytdsection;
    private String updated;

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public Integer getEtaId() {
        return etaId;
    }

    public void setEtaId(Integer etaId) {
        this.etaId = etaId;
    }

    public String getNytdsection() {
        return nytdsection;
    }

    public void setNytdsection(String nytdsection) {
        this.nytdsection = nytdsection;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}
