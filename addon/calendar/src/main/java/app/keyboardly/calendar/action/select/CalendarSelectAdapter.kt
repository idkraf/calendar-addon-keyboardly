package app.keyboardly.calendar.action.select

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.keyboardly.calendar.action.list.CalendarListAdapter
import app.keyboardly.calendar.databinding.ItemKalendarBinding
import app.keyboardly.calendar.models.GoogleCalendar
import app.keyboardly.lib.navigation.NavigationCallback

class CalendarSelectAdapter(
    private val context: Context,
    private val list:List<GoogleCalendar>,
    private val navigationCallback: NavigationCallback
) : RecyclerView.Adapter<CalendarSelectAdapter.ViewHolder>() {


    class ViewHolder(
        view: ItemKalendarBinding,
    ):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}