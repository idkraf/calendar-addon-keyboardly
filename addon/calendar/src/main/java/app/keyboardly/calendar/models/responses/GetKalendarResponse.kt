package app.keyboardly.calendar.models.responses

import java.io.Serializable

class GetKalendarResponse : Serializable {
    var id: Long = 0
    var outputGoogleAuthId: String? = null
    var outputCalendarId: String? = null
    var inputGoogleCalendars: List<String>? = null

    constructor() {}
    constructor(outputGoogleAuthId: String?, outputCalendarId: String?) {
        this.outputGoogleAuthId = outputGoogleAuthId
        this.outputCalendarId = outputCalendarId
    }

    constructor(
        outputGoogleAuthId: String?,
        outputCalendarId: String?,
        inputGoogleCalendars: List<String>?
    ) {
        this.outputGoogleAuthId = outputGoogleAuthId
        this.outputCalendarId = outputCalendarId
        this.inputGoogleCalendars = inputGoogleCalendars
    }
}