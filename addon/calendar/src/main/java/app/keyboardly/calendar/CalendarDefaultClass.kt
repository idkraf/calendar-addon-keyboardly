package app.keyboardly.calendar

import android.content.Intent
import app.keyboardly.calendar.action.list.CalendarListActionView
import app.keyboardly.calendar.action.login.LoginCalendarActionView
import app.keyboardly.calendar.models.KalPref
import app.keyboardly.calendar.services.GoogleService
import app.keyboardly.lib.DefaultClass
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.navigation.NavigationMenuModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo

class CalendarDefaultClass(
    dependency: KeyboardActionDependency
) : DefaultClass(dependency){

    private lateinit var firebaseAuth: FirebaseAuth
    private val kalPref: KalPref? = null
    private val list = CalendarListActionView(dependency)
    private val login = LoginCalendarActionView(dependency)
    override fun getSubmenus(): MutableList<NavigationMenuModel> {
        return mutableListOf()
    }

    override fun onCreate() {

        //GoogleService.getInstance().getGoogleApiClient().connect();
        initFirebase()
        //init()
    }
    private fun initFirebase(){
        FirebaseApp.initializeApp(dependency.getContext())
        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }

        if(user!=null){
            dependency.setActionView(list)
        }else{
            dependency.setActionView(login)
            //dependency.getContext().startActivity(Intent(getContext(), LoginCalendarActionView::class.java))
        }
    }

    private fun init(){
        val islogin = kalPref?.isUserSignedIn
        //cek if login
        if (islogin == true){
            //viewLayout = list.getView()
            dependency.setActionView(list)
        }else{
            dependency.setActionView(login)
            //dependency.getContext().startActivity(Intent(getContext(), LoginCalendarActionView::class.java))
        }
    }
}