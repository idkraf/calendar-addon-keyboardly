package app.keyboardly.calendar.action.list

import androidx.recyclerview.widget.LinearLayoutManager
import app.keyboardly.calendar.databinding.ActivityCalendarMainBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView

class CalendarListActionView(
    dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {

    private lateinit var binding: ActivityCalendarMainBinding

    override fun onCreate() {
        binding = ActivityCalendarMainBinding.inflate(getLayoutInflater())
        viewLayout = binding.root
        initAction()
    }

    private fun initAction() {
        binding.apply {

            back.apply {
                setOnClickListener {
                    dependency.viewAddOnNavigation()
                }
            }

            backBtn.apply {
                setOnClickListener {
                    dependency.viewAddOnNavigation()
                }
            }
            addEvent.apply {
                setOnClickListener {

                }
            }
            viewKalendars.apply {
                layoutManager = LinearLayoutManager(context)

            }
        }
    }
}