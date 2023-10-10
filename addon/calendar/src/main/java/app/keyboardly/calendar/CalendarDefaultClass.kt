package app.keyboardly.calendar

import app.keyboardly.calendar.action.list.CalendarListActionView
import app.keyboardly.calendar.action.login.LoginCalendarActionView
import app.keyboardly.calendar.models.KalPref
import app.keyboardly.lib.DefaultClass
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.navigation.NavigationMenuModel


class CalendarDefaultClass(
    dependency: KeyboardActionDependency
) : DefaultClass(dependency){

    private val kalPref: KalPref? = null
    private val list = CalendarListActionView(dependency)
    private val login = LoginCalendarActionView(dependency)

    override fun getSubmenus(): MutableList<NavigationMenuModel> {
        return mutableListOf()
    }

    override fun onCreate() {

        init()
    }

    private fun init(){
        val islogin = kalPref?.isUserSignedIn
        //cek if login
        if (islogin == true){
            //viewLayout = list.getView()
            dependency.setActionView(list)
        }else{
            dependency.setActionView(login)
        }
    }
}