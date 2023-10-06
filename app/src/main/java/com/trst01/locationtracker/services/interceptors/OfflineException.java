package com.trst01.locationtracker.services.interceptors;

import java.io.IOException;

/**
 * Created by telekha on 15/3/17.
 */

public class OfflineException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}
