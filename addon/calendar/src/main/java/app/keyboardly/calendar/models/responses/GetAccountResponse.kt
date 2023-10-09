package app.keyboardly.calendar.models.responses

import app.keyboardly.calendar.models.GoogleAuth

class GetAccountResponse {
    var id: Long = 0
    var userEmail: String? = null
    var googleAuths: List<GoogleAuth>? = null

    constructor() {}
    constructor(id: Long, userEmail: String?, googleAuths: List<GoogleAuth>?) {
        this.id = id
        this.userEmail = userEmail
        this.googleAuths = googleAuths
    }
}