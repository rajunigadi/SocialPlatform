package com.raju.socialplatform.utilities

import android.support.v7.widget.AppCompatEditText

object ValidationUtil {
    fun hasText(editText: AppCompatEditText): Boolean {

        val text = editText.text.toString().trim { it <= ' ' }
        editText.error = null

        // length 0 means there is no text
        if (text.length == 0) {
            editText.error = "Fields cannot be empty."
            return false
        }

        return true
    }
}
