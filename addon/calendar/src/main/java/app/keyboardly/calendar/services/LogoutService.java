package app.keyboardly.calendar.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import app.keyboardly.calendar.R;
import app.keyboardly.calendar.action.login.LoginCalendarActionView;
import app.keyboardly.calendar.models.KalPref;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import static app.keyboardly.calendar.services.GoogleService.finish;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LogoutService {

    KalPref kalPref;

    public void logOut(Context context) {

        kalPref = new KalPref(context);

        GoogleService.getInstance().getGoogleApiClient().connect();
        GoogleService.getInstance().getGoogleApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {

            @Override
            public void onConnected(@Nullable Bundle bundle) {
                if (GoogleService.getInstance().getGoogleApiClient().isConnected()) {
                    Auth.GoogleSignInApi.signOut(GoogleService.getInstance().getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.d("Log out", "User Logged out");
                                //Toast.makeText(context, R.string.successful_logout, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, LoginCalendarActionView.class);
                                intent.putExtra("isLoggedOut", true);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.d("Connection suspended", "Google API Client Connection Suspended");
            }
        });
        kalPref.clearAccountsAndAll();
    }
}
