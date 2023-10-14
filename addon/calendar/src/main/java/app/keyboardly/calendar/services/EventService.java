package app.keyboardly.calendar.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.keyboardly.calendar.http.GoogleApi;
import app.keyboardly.calendar.models.GoogleAuth;
import app.keyboardly.calendar.models.GoogleCalendar;
import app.keyboardly.calendar.models.KalPref;
import app.keyboardly.calendar.models.event.EventResponse;
import app.keyboardly.calendar.models.event.PreviewEvent;
import retrofit2.Response;

public class EventService extends IntentService {

    private List<PreviewEvent> eventsFromGoogle = new ArrayList<>();
    private List<PreviewEvent> weekViewEvents = new ArrayList<>();
    private List<GoogleCalendar> googleCalendars = new ArrayList<>();
    private List<String> authorizations = new ArrayList<>();
    private KalPref kalPref;

    GoogleApi googleApi;

    public EventService() {
        super("EventService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        kalPref = new KalPref(this);
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));
            String accessToken = googleAuth.getAccessToken();
            authorizations.add("Bearer " + accessToken);
        }

        Bundle bundle = intent.getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");

        Calendar beginning = Calendar.getInstance();
        Calendar end = (Calendar) beginning.clone();
        beginning.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        beginning.set(Calendar.MONTH, 0);
        beginning.set(Calendar.DAY_OF_MONTH, 1);
        Date start = beginning.getTime();

        end.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        end.set(Calendar.MONTH, 11);
        end.set(Calendar.DAY_OF_MONTH, 31);
        Date finish = end.getTime();

        DateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String firstDayOfCurrentYear = startDateFormat.format(start);
        String lastDayOfCurrentYear = startDateFormat.format(finish);


        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));
            String authorization = "Bearer " + googleAuth.getAccessToken();

            for (GoogleCalendar googleCalendar : googleCalendars) {
                try {
                    Response<EventResponse> result = googleApi.getEventList(authorization, googleCalendar.getId(), firstDayOfCurrentYear, lastDayOfCurrentYear).execute();
                    eventsFromGoogle.addAll(result.body().getItems());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (PreviewEvent event : eventsFromGoogle) {
            weekViewEvents.add(event);
        }

        Intent backgroundIntent = new Intent("weekViewEvents");
        backgroundIntent.putExtra("weekViewEvents", (Serializable) weekViewEvents);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(backgroundIntent);

    }
}
