package com.popularapi.api;

import com.popularapi.model.email.EmailResponse;
import com.popularapi.model.share.ShareResponse;
import com.popularapi.model.view.ViewResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(URLs.EMAILED)
    Call<EmailResponse> getEmail();

    @GET(URLs.VIEWED)
    Call<ViewResponse> getView();

    @GET(URLs.SHARED)
    Call<ShareResponse> getShared();

    @GET(URLs.SHARED_EMAIL)
    Call<ShareResponse> getSharedEmail();

    @GET(URLs.SHARED_FACEBOOK)
    Call<ShareResponse> getSharedFacebook();

    @GET(URLs.SHARED_TWITTER)
    Call<ShareResponse> getSharedTwitter();

}
