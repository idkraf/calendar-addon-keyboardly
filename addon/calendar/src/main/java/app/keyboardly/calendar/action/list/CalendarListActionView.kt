package app.keyboardly.calendar.action.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.keyboardly.calendar.action.login.LoginCalendarActionView
import app.keyboardly.calendar.action.profil.ProfilActionView
import app.keyboardly.calendar.action.select.CalendarSelectActionView
import app.keyboardly.calendar.databinding.ActivityCalendarMainBinding
import app.keyboardly.calendar.http.GoogleApi
import app.keyboardly.calendar.models.GoogleAuth
import app.keyboardly.calendar.models.GoogleCalendar
import app.keyboardly.calendar.models.KalPref
import app.keyboardly.calendar.models.event.PreviewEvent
import app.keyboardly.calendar.models.responses.GoogleCalendarsResponse
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class CalendarListActionView(
    dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {

    private lateinit var binding: ActivityCalendarMainBinding

    private lateinit var adapterCalendar:CalendarListAdapter
    private var floatingRv: RecyclerView? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val kalPref: KalPref? = null
    private val FIRST_ATTEMPT = 1
    private val FINAL_ATTEMPT = 2
    private val ATTEMPT_STEP = 1
    private var googleCalendars: List<GoogleCalendar> = ArrayList()
    private val eventsFromGoogle: List<PreviewEvent> = ArrayList()
    private val weekViewEvents: List<PreviewEvent> = ArrayList()
    var googleApi: GoogleApi? = null

    private val profil = ProfilActionView(dependency)
    private val pilih = CalendarSelectActionView(dependency)

    override fun onCreate() {
        binding = ActivityCalendarMainBinding.inflate(getLayoutInflater())
        viewLayout = binding.root

        firebaseAuth = FirebaseAuth.getInstance()
        initAction()
    }


    private fun requestCalendars(attempt: Int) {
        val user = firebaseAuth.currentUser
        val authorization = "Bearer " + user?.getIdToken(true)
        Timber.e("auth: $authorization")
        googleApi?.getCalendarList(authorization)
            ?.enqueue(object : Callback<GoogleCalendarsResponse?> {
                override fun onResponse(
                    call: Call<GoogleCalendarsResponse?>,
                    response: Response<GoogleCalendarsResponse?>
                ) {
                    googleCalendars = response.body()?.getItems()!!
                    adapterCalendar.updateList(googleCalendars)
                    Timber.e("size g: "+googleCalendars.size)
                }

                override fun onFailure(call: Call<GoogleCalendarsResponse?>, t: Throwable) {
                    t.printStackTrace()
                    Timber.e("auth: ${t.message}")
                }
            })
    }

    private fun initAction() {
        val user = firebaseAuth.currentUser

        adapterCalendar = CalendarListAdapter(getContext(), googleCalendars,
            object : CalendarListAdapter.CalendarCallback {
                override fun onCopy(data: GoogleCalendar) {
                    TODO("Not yet implemented")
                }

                override fun onView(data: GoogleCalendar) {
                    TODO("Go to Selected Calendar")

                }

                override fun onDetele(data: GoogleCalendar) {
                    TODO("Not yet implemented")
                }

                override fun onSend(data: GoogleCalendar) {
                    TODO("Not yet implemented")
                }
            })

        requestCalendars(FIRST_ATTEMPT)

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
                //setImageURI(user?.photoUrl)
                setOnClickListener {
                    dependency.setActionView(profil)
                }
            }

            addEvent.apply {
                setOnClickListener {
                    dependency.setActionView(pilih)
                }
            }

            viewKalendars.apply {
                floatingRv = this
                layoutManager = LinearLayoutManager(context)
                adapter = adapterCalendar
            }
        }

    }
}