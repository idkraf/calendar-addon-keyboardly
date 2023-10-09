package app.keyboardly.calendar.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class GoogleCalendar : Parcelable, Serializable {
    var id: String? = null
    var summary: String? = null
    var sharingOption: VisibilityOption? = null
    var email: String? = null

    constructor() {}
    constructor(`in`: Parcel) {
        id = `in`.readString()
        summary = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(summary)
    }

    companion object {
        val CREATOR: Parcelable.Creator<GoogleCalendar?> =
            object : Parcelable.Creator<GoogleCalendar?> {
                override fun createFromParcel(`in`: Parcel): GoogleCalendar? {
                    return GoogleCalendar(`in`)
                }

                override fun newArray(size: Int): Array<GoogleCalendar?> {
                    return arrayOfNulls(size)
                }
            }
    }

    object CREATOR : Parcelable.Creator<GoogleCalendar> {
        override fun createFromParcel(parcel: Parcel): GoogleCalendar {
            return GoogleCalendar(parcel)
        }

        override fun newArray(size: Int): Array<GoogleCalendar?> {
            return arrayOfNulls(size)
        }
    }
}