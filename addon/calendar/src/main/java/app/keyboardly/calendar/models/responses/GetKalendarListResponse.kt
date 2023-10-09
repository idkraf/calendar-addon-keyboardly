package app.keyboardly.calendar.models.responses

class GetKalendarListResponse {
    var kalendars: List<GetKalendarResponse>? = null

    constructor() {}
    constructor(kalendars: List<GetKalendarResponse>?) {
        this.kalendars = kalendars
    }
}