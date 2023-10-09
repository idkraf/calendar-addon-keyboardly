package app.keyboardly.calendar.action.login

import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import app.keyboardly.calendar.*
import app.keyboardly.calendar.databinding.ActivityCalendarLoginBinding
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.KeyboardActionView
import app.keyboardly.lib.helper.OnResult

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
) : KeyboardActionView(dependency), OnResult{

    private lateinit var binding: ActivityCalendarLoginBinding

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate() {
        binding = ActivityCalendarLoginBinding.inflate(getLayoutInflater());
        viewLayout = binding.root

        FirebaseApp.initializeApp(dependency.getContext())

        //val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        //    .requestIdToken(R.string.default_web_client_id)
        //    .requestEmail()
        //    .build()

        //mGoogleSignInClient = GoogleSignIn.getClient(dependency.getContext(), gso)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {

            buttonLogin.setOnClickListener {
                signInGoogle()
            }
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        dependency.startActivityForResult(signInIntent, Req_Code)
        //startActivityForResult(signInIntent, Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Req_Code) {
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
            toast(e.toString())
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        //save account to preference
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){

            }
        }
    }

}