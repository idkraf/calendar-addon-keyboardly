package app.keyboardly.calendar.action.profil

import app.keyboardly.calendar.databinding.ActivityCalendarSelectBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView

class ProfilActionView(
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