package app.keyboardly.calendar.action.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.keyboardly.calendar.databinding.ItemKalendarBinding
import app.keyboardly.calendar.models.responses.GetKalendarListResponse
import app.keyboardly.calendar.models.responses.GetKalendarResponse
import app.keyboardly.lib.navigation.NavigationCallback

class CalendarListAdapter(
    private val context: Context,
    private val list:List<GetKalendarListResponse>,
    private val navigationCallback: NavigationCallback
) :RecyclerView.Adapter<CalendarListAdapter.ViewHolder>(){

    class ViewHolder(
        view: ItemKalendarBinding,
    ):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}