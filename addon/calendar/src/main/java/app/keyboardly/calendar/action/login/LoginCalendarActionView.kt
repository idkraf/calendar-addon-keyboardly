package app.keyboardly.calendar.action.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import app.keyboardly.calendar.CalendarDefaultClass
import app.keyboardly.calendar.databinding.ActivityCalendarLoginBinding
import app.keyboardly.calendar.models.GoogleAuth
import app.keyboardly.calendar.models.KalPref
import app.keyboardly.dev.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber
class LoginCalendarActionView: AppCompatActivity(){

    private lateinit var binding: ActivityCalendarLoginBinding
    private val kalPref: KalPref? = null
    private val googleAuth: GoogleAuth? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this@LoginCalendarActionView)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(this.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this@LoginCalendarActionView, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            buttonLogin.setOnClickListener {
                signInGoogle()
            }
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent);
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this@LoginCalendarActionView, e.message,
                Toast.LENGTH_SHORT).show()
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
                    this.apply {
                        //onBackPressed()
                        //finish()
                        val intent = Intent(this@LoginCalendarActionView, CalendarDefaultClass::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                }
            }
        }
    }

}