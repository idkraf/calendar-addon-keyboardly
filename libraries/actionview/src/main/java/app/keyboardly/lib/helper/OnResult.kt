package app.keyboardly.lib.helper

import android.content.Intent

interface OnResult {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}