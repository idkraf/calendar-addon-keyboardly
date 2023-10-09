package app.keyboardly.calendar.models.responses

class PostKalendarResponse {
    var kalendarId: String? = null
    var status: String? = null

    constructor(kalendarId: String?, status: String?) {
        this.kalendarId = kalendarId
        this.status = status
    }

    constructor() {}
}