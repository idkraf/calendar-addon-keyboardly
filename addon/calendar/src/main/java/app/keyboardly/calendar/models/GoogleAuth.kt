package app.keyboardly.calendar.models

class GoogleAuth {
    var authCode: String? = null
    var email: String? = null
    var displayName: String? = null
    var accessToken: String? = null

    constructor(authCode: String?, email: String?, displayName: String?) {
        this.authCode = authCode
        this.email = email
        this.displayName = displayName
    }

    constructor() {}
}