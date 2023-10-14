package app.keyboardly.calendar.action.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import app.keyboardly.calendar.databinding.ItemKalendarBinding
import app.keyboardly.calendar.models.GoogleCalendar
import app.keyboardly.lib.navigation.NavigationCallback
import org.w3c.dom.Text


class CalendarListAdapter(
    private val context: Context,
    private var list: List<GoogleCalendar>, //private val list:List<GetKalendarListResponse>,
    private var navCallback: CalendarCallback
) :RecyclerView.Adapter<CalendarListAdapter.ViewHolder>(){

    fun addGoogleCalendars(newGoogleCalendars: List<GoogleCalendar>, email: String?) {
        for (googleCalendar in newGoogleCalendars) {
            googleCalendar.email = email
        }
        this.list = newGoogleCalendars
        notifyDataSetChanged()
    }
    fun updateList(list: List<GoogleCalendar>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: ItemKalendarBinding,
    ):RecyclerView.ViewHolder(view.root){
        val lihat:ConstraintLayout = view.viewCalendar
        val judul:TextView = view.textKalendarJudul
        val tgl:TextView = view.textKalendarTgl
        val kirim:ImageButton = view.csend
        val copy:ImageButton = view.ccopy
        val hapus:ImageButton = view.cdelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(context)
        val binding = ItemKalendarBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder){
            data.apply {
                judul.text = summary
                lihat.setOnClickListener {
                    navCallback.onView(data)
                }
                kirim.setOnClickListener{
                    navCallback.onSend(data)
                }
                copy.setOnClickListener{
                    navCallback.onCopy(data)
                }
                hapus.setOnClickListener {
                    navCallback.onDetele(data)
                }
            }
        }
    }
    interface CalendarCallback{
        fun onView(data: GoogleCalendar)
        fun onDetele(data:GoogleCalendar)
        fun onSend(data:GoogleCalendar)
        fun onCopy(data:GoogleCalendar)
    }
}