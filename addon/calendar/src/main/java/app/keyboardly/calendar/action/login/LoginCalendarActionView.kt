package app.keyboardly.calendar.action.login

import android.app.Activity
import android.content.Intent
import androidx.activity.result.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import app.keyboardly.calendar.action.list.CalendarListActionView
import app.keyboardly.calendar.databinding.ActivityCalendarLoginBinding
import app.keyboardly.calendar.models.GoogleAuth
import app.keyboardly.calendar.models.KalPref
import app.keyboardly.dev.HomeActivity
import app.keyboardly.dev.R
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginCalendarActionView(
dependency: KeyboardActionDependency
) : KeyboardActionView(dependency){

    private lateinit var binding: ActivityCalendarLoginBinding

    private val list = CalendarListActionView(dependency)

    private val kalPref: KalPref? = null
    private val googleAuth: GoogleAuth? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var onResult: OnResult
    private lateinit var activity: HomeActivity

    override fun onCreate() {
        binding = ActivityCalendarLoginBinding.inflate(getLayoutInflater());
        viewLayout = binding.root
        activity = HomeActivity()
        FirebaseApp.initializeApp(dependency.getContext())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(dependency.getContext().getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(dependency.getContext(), gso)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            backBtn.setOnClickListener {
                dependency.viewAddOnNavigation()
            }
            buttonLogin.setOnClickListener {
                signInGoogle()
            }
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent);

        //activity.getActivityResultManager().put(Req_Code, OnResult)
        //dependency.startActivityForResult(signInIntent, Req_Code)
        //activity.startActivityForResult(signInIntent, Req_Code)
        //{
           // val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(signInIntent)
           // handleResult(task)
        //}
        //dependency.getContext().startActivity()
    }

    var resultLauncher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    //override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
   //     if (requestCode == Req_Code) {
   //         val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
   //         handleResult(task)
   //     }
    //}

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            toast(e.toString())
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        //save account to preference
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){

                kalPref?.setClienttoken(account.idToken)
                if (kalPref?.getAkun()?.contains(account.email) == false){
                    googleAuth?.setCalendarEmail(account.email)
                    googleAuth?.setCalendarDisplayName(account.displayName)
                    googleAuth?.setCalendarAuthCode(account.serverAuthCode)
                    //googleAuth?.setCalendarAccessToken(account.)
                    if (googleAuth != null) {
                        kalPref.putAuth(googleAuth)
                    }

                    //move to list
                    dependency.setActionView(list)
                }
            }
        }
    }

}