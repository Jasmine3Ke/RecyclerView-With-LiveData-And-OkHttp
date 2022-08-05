package com.example.recyclerview_with_livedata_okhttp.variables;

import com.example.recyclerview_with_livedata_okhttp.BuildConfig;

public class GlobalVal {
    private final static String hostApiVer = "api/v3/ticker/24hr";
    private final static String URL = BuildConfig.base_url + hostApiVer;

    public static String getURL() {
        return URL;
    }
}
