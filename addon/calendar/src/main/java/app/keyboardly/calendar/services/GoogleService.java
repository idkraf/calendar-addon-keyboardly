package app.keyboardly.calendar.services;

import com.google.android.gms.common.api.GoogleApiClient;

public class GoogleService {

    private static GoogleApiClient googleApiClient = null;
    private static GoogleService singleton = null;

    protected GoogleService(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public static GoogleService getInstance() {
        if(singleton == null) {
            throw new AssertionError("You have to call init first");
        }
        return singleton;
    }

    public synchronized static GoogleService init(GoogleApiClient googleApiClient) {
        if(singleton == null) {
            singleton = new GoogleService(googleApiClient);
        }
        return singleton;
    }

    public static void finish(){
        singleton = null;
        googleApiClient = null;
    }


    public static GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

}
