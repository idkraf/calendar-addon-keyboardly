package app.keyboardly.calendar.http

import app.keyboardly.calendar.models.event.EventResponse
import app.keyboardly.calendar.models.responses.GoogleCalendarsResponse
import retrofit2.Call
import retrofit2.http.*

interface GoogleApi {

    @Headers("Accept: application/json")
    @GET("calendarList")
    fun getCalendarList(@Header("Authorization") authorization: String?): Call<GoogleCalendarsResponse?>?

    @Headers("Accept: application/json")
    @GET("{calendarId}/events")
    fun getEventList(
        @Header("Authorization") authorization: String?,
        @Path("calendarId") calendarId: String?,
        @Query("timeMin") timeMin: String?,
        @Query("timeMax") timeMax: String?
    ): Call<EventResponse?>?

}