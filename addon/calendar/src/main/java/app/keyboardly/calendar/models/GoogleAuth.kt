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


    fun getCalendarDisplayName(): String? {
        return displayName
    }

    fun setCalendarDisplayName(displayName: String?) {
        this.displayName = displayName
    }

    fun getCalendarAuthCode(): String? {
        return authCode
    }

    fun setCalendarAuthCode(authCode: String?) {
        this.authCode = authCode
    }

    fun getCalendarEmail(): String? {
        return email
    }

    fun setCalendarEmail(email: String?) {
        this.email = email
    }

    fun getCalendarAccessToken(): String? {
        return accessToken
    }

    fun setCalendarAccessToken(accessToken: String?) {
        this.accessToken = accessToken
    }
}