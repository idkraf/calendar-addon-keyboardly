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


    @JvmName("getKind1")
    fun getKind(): String? {
        return kind
    }

    @JvmName("setKind1")
    fun setKind(kind: String?) {
        this.kind = kind
    }

    @JvmName("getEtag1")
    fun getEtag(): String? {
        return etag
    }

    @JvmName("setEtag1")
    fun setEtag(etag: String?) {
        this.etag = etag
    }

    @JvmName("getNextPageToken1")
    fun getNextPageToken(): String? {
        return nextPageToken
    }

    @JvmName("setNextPageToken1")
    fun setNextPageToken(nextPageToken: String?) {
        this.nextPageToken = nextPageToken
    }

    @JvmName("getNextSyncToken1")
    fun getNextSyncToken(): String? {
        return nextSyncToken
    }

    @JvmName("setNextSyncToken1")
    fun setNextSyncToken(nextSyncToken: String?) {
        this.nextSyncToken = nextSyncToken
    }

    @JvmName("getItems1")
    fun getItems(): List<GoogleCalendar>? {
        return items
    }

    @JvmName("setItems1")
    fun setItems(items: List<GoogleCalendar>?) {
        this.items = items
    }
}