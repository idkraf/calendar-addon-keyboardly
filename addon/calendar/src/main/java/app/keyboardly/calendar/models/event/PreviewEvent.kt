package app.keyboardly.calendar.models.event

import java.io.Serializable

class PreviewEvent : Serializable {
    var id: String? = null
    var created: String? = null
    var updated: String? = null
    var summary: String? = null
    var start: StartTime? = null
    var end: EndTime? = null

    constructor() {}
    constructor(
        id: String?,
        created: String?,
        updated: String?,
        summary: String?,
        start: StartTime?,
        end: EndTime?
    ) {
        this.id = id
        this.created = created
        this.updated = updated
        this.summary = summary
        this.start = start
        this.end = end
    }
}