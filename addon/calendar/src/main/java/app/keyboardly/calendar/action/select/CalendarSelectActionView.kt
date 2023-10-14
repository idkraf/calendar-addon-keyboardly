package app.keyboardly.calendar.action.select

import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import app.keyboardly.calendar.databinding.ActivityCalendarSelectBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView
import app.keyboardly.lib.helper.InputPresenter

class CalendarSelectActionView(
dependency: KeyboardActionDependency
) : KeyboardActionView(dependency), InputPresenter {
    private lateinit var binding: ActivityCalendarSelectBinding

    override fun onCreate() {
        binding = ActivityCalendarSelectBinding.inflate(getLayoutInflater())
        viewLayout = binding.root
        initAction()
    }

    private fun initAction() {
        binding.apply {
            backBtn.setOnClickListener {
                dependency.viewAddOnNavigation()
            }
            title.setOnClickListener {
                dependency.requestInput(title, this@CalendarSelectActionView)
            }
            description.setOnClickListener {
                dependency.requestInput(description, this@CalendarSelectActionView,
                longInput = true
                )
            }

            isAllDay.apply {
                setOnClickListener{
                    if(isChecked){
                        allDay.visibility = View.VISIBLE
                        separateTime.visibility = View.GONE
                        defaultTime.visibility = View.GONE
                    }else{
                        allDay.visibility = View.GONE
                        if(!isAllDay.isChecked && !isSeparate.isChecked){
                            defaultTime.visibility = View.VISIBLE
                        }
                    }
                }

            }

            isSeparate.apply {

                setOnClickListener {
                    if (isChecked) {
                        allDay.visibility = View.GONE
                        separateTime.visibility = View.VISIBLE
                        defaultTime.visibility = View.GONE
                    } else {
                        separateTime.visibility = View.GONE
                        if (!isAllDay.isChecked && !isSeparate.isChecked) {
                            defaultTime.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDone(text: String, editText: EditText?) {
        Log.d("onedit ", "text: $text")
        val prefixDate = "date:"
        if(text.startsWith(prefixDate)){
            val dateSelected = text.replace(prefixDate, "")
            editText?.setText(dateSelected)
        }else{
            editText?.setText(text)
        }
        dependency.viewLayoutAction()
    }
}