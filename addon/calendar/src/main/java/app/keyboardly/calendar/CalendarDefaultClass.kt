package app.keyboardly.calendar

import app.keyboardly.calendar.action.list.CalendarListActionView
import app.keyboardly.calendar.action.login.LoginCalendarActionView
import app.keyboardly.lib.DefaultClass
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.navigation.NavigationMenuModel

class CalendarDefaultClass(
    dependency: KeyboardActionDependency
) : DefaultClass(dependency){

    private val list = CalendarListActionView(dependency)
    private val login = LoginCalendarActionView(dependency)

    override fun getSubmenus(): MutableList<NavigationMenuModel> {
        return mutableListOf()
    }

    override fun onCreate() {
       //cek if login
       viewLayout = list.getView()
    }

    private fun init(){

    }
}