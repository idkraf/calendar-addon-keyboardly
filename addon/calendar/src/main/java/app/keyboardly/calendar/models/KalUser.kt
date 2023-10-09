package app.keyboardly.calendar.models

class KalUser {
    var id: Long = 0
    var clientToken: String? = null
    var userEmail: String? = null
    var accessToken: String? = null

    constructor() {}
    constructor(userId: Long, clientToken: String?, userEmail: String?, accessToken: String?) {
        id = userId
        this.clientToken = clientToken
        this.userEmail = userEmail
        this.accessToken = accessToken
    }
}