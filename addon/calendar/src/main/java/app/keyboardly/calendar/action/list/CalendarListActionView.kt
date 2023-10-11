package app.keyboardly.calendar.action.list

import androidx.recyclerview.widget.LinearLayoutManager
import app.keyboardly.calendar.databinding.ActivityCalendarMainBinding
import app.keyboardly.calendar.models.KalPref
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView
import com.google.firebase.auth.FirebaseAuth

class CalendarListActionView(
    dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {

    private lateinit var binding: ActivityCalendarMainBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private val kalPref: KalPref? = null

    override fun onCreate() {
        binding = ActivityCalendarMainBinding.inflate(getLayoutInflater())
        viewLayout = binding.root
        initAction()
    }

    private fun initAction() {
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

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

            akunMore.apply {
                setImageURI(user?.photoUrl)
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