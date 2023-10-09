package app.keyboardly.calendar.action.profil

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.keyboardly.calendar.databinding.ItemAccountBinding
import app.keyboardly.calendar.models.GoogleAuth
import app.keyboardly.lib.navigation.NavigationCallback

class ProfilListAdapter(
    private val context: Context,
    private val list:List<GoogleAuth>,
    private val navigationCallback: NavigationCallback
) :RecyclerView.Adapter<ProfilListAdapter.ViewHolder>(){

    class ViewHolder(
        view: ItemAccountBinding,
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