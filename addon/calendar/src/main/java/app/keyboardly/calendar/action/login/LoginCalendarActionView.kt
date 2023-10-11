package app.keyboardly.calendar.action.login

import android.content.Intent
import app.keyboardly.calendar.databinding.ActivityCalendarLoginBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView
import com.google.firebase.auth.FirebaseAuth
class LoginCalendarActionView(
    dependency: KeyboardActionDependency
) : KeyboardActionView(dependency) {

    private lateinit var binding: ActivityCalendarLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate() {
        binding = ActivityCalendarLoginBinding.inflate(getLayoutInflater())
        viewLayout = binding.root
        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            backBtn.setOnClickListener {
                dependency.viewAddOnNavigation()
            }
            buttonLogin.setOnClickListener {
                dependency.getContext().startActivity(Intent(getContext(), LoginCalendarAction::class.java))
            }
        }

        val user = firebaseAuth.currentUser
        if(user!=null){
            dependency.viewAddOnNavigation()
        }
    }


    override fun onResume() {
        super.onResume()
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if(user!=null){
            dependency.viewAddOnNavigation()
        }
    }
}