package app.keyboardly.dev.keyboard.keypad

import android.content.Intent
import app.keyboardly.lib.helper.OnResult

class ActivityResultManager : HashMap<Int?, OnResult?>() {
    fun dispatch(requestCode: Int, resultCode: Int, data: Intent?) {
        val handler: OnResult? = get(requestCode)
        handler?.onActivityResult(requestCode, resultCode, data)
    }
}