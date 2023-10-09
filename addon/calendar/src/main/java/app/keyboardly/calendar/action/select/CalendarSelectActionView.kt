package app.keyboardly.calendar.action.select

import app.keyboardly.calendar.databinding.ActivityCalendarSelectBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView

class CalendarSelectActionView(
dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {
    private lateinit var binding: ActivityCalendarSelectBinding

    override fun onCreate() {
        binding = ActivityCalendarSelectBinding.inflate(getLayoutInflater())
        viewLayout = binding.root
        initAction()
    }

    private fun initAction() {
        binding.apply {

        }
    }
}