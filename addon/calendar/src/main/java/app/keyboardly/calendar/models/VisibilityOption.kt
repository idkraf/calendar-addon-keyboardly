package app.keyboardly.calendar.models

enum class VisibilityOption(private val visibilities: String) {
    DEFAULT("default"), PUBLIC("public"), PRIVATE("private");

    override fun toString(): String {
        return visibilities
    }
}