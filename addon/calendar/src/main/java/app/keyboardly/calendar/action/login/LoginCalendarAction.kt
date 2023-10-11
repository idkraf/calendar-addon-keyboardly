package app.keyboardly.calendar.action.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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

class LoginCalendarAction: AppCompatActivity(){

    private val kalPref: KalPref? = null
    private val googleAuth: GoogleAuth? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this@LoginCalendarAction)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(this.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this@LoginCalendarAction, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        //start signin
        signInGoogle()
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
            Toast.makeText(this@LoginCalendarAction, e.message,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {

        val photo = account.photoUrl
        val email = account.email
        val name = account.displayName
        val token = account.idToken
        val authCode = account.serverAuthCode

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)

        //if (kalPref?.getAkun()?.contains(account.email) == false){
            googleAuth?.setCalendarEmail(email)
            googleAuth?.setCalendarDisplayName(name)
            googleAuth?.setCalendarAuthCode(authCode)
            googleAuth?.setCalendarDisplayFoto(photo.toString())
            googleAuth?.setCalendarAccessToken(credential.toString())

            if (googleAuth != null) {
                kalPref?.putAuth(googleAuth)
            }
            kalPref?.setClienttoken(token)


            //move to list
            this.apply {
                //onBackPressed()
                finish()
            }
        //}
    }

}