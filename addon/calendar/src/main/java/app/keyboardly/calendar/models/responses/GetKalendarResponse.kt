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

    @JvmName("getId1")
    fun getId(): Long {
        return id
    }

    @JvmName("setId1")
    fun setId(id: Long) {
        this.id = id
    }

    @JvmName("getOutputGoogleAuthId1")
    fun getOutputGoogleAuthId(): String? {
        return outputGoogleAuthId
    }

    @JvmName("setOutputGoogleAuthId1")
    fun setOutputGoogleAuthId(outputGoogleAuthId: String?) {
        this.outputGoogleAuthId = outputGoogleAuthId
    }

    @JvmName("getOutputCalendarId1")
    fun getOutputCalendarId(): String? {
        return outputCalendarId
    }

    @JvmName("setOutputCalendarId1")
    fun setOutputCalendarId(outputCalendarId: String?) {
        this.outputCalendarId = outputCalendarId
    }

    @JvmName("getInputGoogleCalendars1")
    fun getInputGoogleCalendars(): List<String>? {
        return inputGoogleCalendars
    }

    @JvmName("setInputGoogleCalendars1")
    fun setInputGoogleCalendars(inputGoogleCalendars: List<String>?) {
        this.inputGoogleCalendars = inputGoogleCalendars
    }
}