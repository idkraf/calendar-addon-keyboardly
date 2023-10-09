package app.keyboardly.calendar.models.event

class EventResponse {
    var kind: String? = null
    var etag: String? = null
    var summary: String? = null
    var description: String? = null
    var updated: String? = null
    var timeZone: String? = null
    var accessRole: String? = null
    var nextPageToken: String? = null
    var nextSyncToken: String? = null
    var items: List<PreviewEvent>? = null

    constructor() {}
    constructor(
        kind: String?, etag: String?, summary: String?, description: String?,
        updated: String?, timeZone: String?, accessRole: String?,
        nextPageToken: String?,
        nextSyncToken: String?, items: List<PreviewEvent>?
    ) {
        this.kind = kind
        this.etag = etag
        this.summary = summary
        this.description = description
        this.updated = updated
        this.timeZone = timeZone
        this.accessRole = accessRole
        this.nextPageToken = nextPageToken
        this.nextSyncToken = nextSyncToken
        this.items = items
    }
}