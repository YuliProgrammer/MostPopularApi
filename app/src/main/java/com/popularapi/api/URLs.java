package com.popularapi.api;

public class URLs {

    public static final String SERVER_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    private static final String KEY = ".json?api-key=11gBCcayzqgAkCCD0hLRxn7uafYHS8aT";
    private static final String _SHARED = "shared/30";

    public static final String EMAILED = "emailed/30" + KEY;
    public static final String SHARED = "shared/30" + KEY;
    public static final String VIEWED = "viewed/30" + KEY;

    public static final String SHARED_EMAIL = _SHARED + "/email" + KEY;
    public static final String SHARED_FACEBOOK = _SHARED + "/facebook" + KEY;
    public static final String SHARED_TWITTER = _SHARED + "/twitter" + KEY;
}
