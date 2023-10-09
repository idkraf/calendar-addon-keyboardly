package app.keyboardly.calendar.models.responses

import app.keyboardly.calendar.models.GoogleCalendar

class GoogleCalendarsResponse {
    var kind: String? = null
    var etag: String? = null
    var nextPageToken: String? = null
    var nextSyncToken: String? = null
    var items: List<GoogleCalendar>? = null

    constructor(
        etag: String?,
        nextPageToken: String?,
        nextSyncToken: String?,
        items: List<GoogleCalendar>?
    ) {
        kind = "calendar#calendarList"
        this.etag = etag
        this.nextPageToken = nextPageToken
        this.nextSyncToken = nextSyncToken
        this.items = items
    }

    constructor() {}
}