package app.keyboardly.calendar.models.event

import android.os.Parcel
import java.io.Serializable
import java.util.*

class StartTime : Serializable {
    var date: String? = null
    var dateTime: Date? = null
    var timeZone: TimeZone? = null

    constructor() {}
    constructor(date: String?, dateTime: Date?, timeZone: TimeZone?) {
        this.date = date
        this.dateTime = dateTime
        this.timeZone = timeZone
    }

    protected constructor(`in`: Parcel) {
        date = `in`.readString()
    }
}