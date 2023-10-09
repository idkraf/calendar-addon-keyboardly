package app.keyboardly.calendar

import android.view.View
import app.keyboardly.lib.navigation.NavigationMenuModel
import app.keyboardly.lib.reflector.DynamicFeature
import app.keyboardly.calendar.di.DaggerDynamicComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DynamicFeatureImpl @Inject constructor(
    private val calendarView: CalendarDefaultClass,
) : DynamicFeature {

    /**
     * The provider singleton. It is accessed from the base app component to create the feature graph
     * using the provided dependencies, and return the feature instance.
     * */
    companion object Provider : DynamicFeature.Provider {
        override fun get(dependencies: DynamicFeature.Dependencies): DynamicFeature {
            return DaggerDynamicComponent.builder()
                .dependencies(dependencies)
                .build()
                .dynamicFeature()
        }
    }

    /**
     * get the default view of add on.
     * it's possible to be set to null, but don't empty View()
     */
    override fun getView(): View? {
        return calendarView.getView()
    }

    override fun getTopView(): View? = null //TopActionView(sampleView.dependency).getView()

    /**
     * submenu of add on, list of NavigationMenuModel
     * it's possible to set to empty mutableList() but getView() not null
     */
    override fun getSubMenus(): MutableList<NavigationMenuModel> {
        return calendarView.getSubmenus()
    }
}
