package app.keyboardly.calendar.models

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

class KalPref(context: Context?) {
    private val sharedPref: SharedPreferences
    var accounts: ArrayList<String>? = null
        private set
    private val gson = Gson()

    init {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val list = sharedPref.getString("accountslist", "")
        if (list == "") {
            accounts = ArrayList()
        } else {
            accounts = gson.fromJson(list, ArrayList::class.java) as ArrayList<String>?
        }
    }

    fun putString(key: String?, value: String?) {
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return sharedPref.getString(key, "")
    }

    fun putAuth(googleAuth: GoogleAuth) {
        val value = gson.toJson(googleAuth)
        putString(googleAuth.email, value)
        googleAuth.email?.let { addAccount(it) }
    }

    fun getAuth(key: String?): GoogleAuth {
        val value = getString(key)
        return gson.fromJson(value, GoogleAuth::class.java)
    }

    private fun addAccount(accountname: String) {
        accounts!!.add(accountname)
        val value = gson.toJson(accounts)
        putString("accountslist", value)
    }

    fun removeAccount(key: String) {
        accounts!!.remove(key)
        val value = gson.toJson(accounts)
        putString("accountslist", value)
    }

    fun getAkun(): ArrayList<String>? {
        return accounts
    }
    fun clearAccountsAndAll() {
        val editor = sharedPref.edit()
        for (i in accounts!!.indices) {
            editor.remove(accounts!![i])
        }
        editor.clear()
        editor.apply()
        accounts = ArrayList()
    }

    val googleAuths: List<GoogleAuth>
        get() {
            val accountNameList = accounts
            val auths = ArrayList<GoogleAuth>()
            for (i in accountNameList!!.indices) {
                val auth = getAuth(accountNameList[i])
                auths.add(auth)
            }
            return auths
        }

    fun clientToken(): String? {
        return getString(CLIENTTOKEN)
    }

    fun setClienttoken(value: String?) {
        putString(CLIENTTOKEN, value)
    }

    val isUserSignedIn: Boolean
        get() = clientToken() != ""

    companion object {
        const val CLIENTTOKEN = "clienttoken"
    }
}