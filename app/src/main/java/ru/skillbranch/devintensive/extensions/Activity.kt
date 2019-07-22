package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
     val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
     val viewHK = this.currentFocus ?: View(this)
     imm.hideSoftInputFromWindow(viewHK.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = findViewById<View>(android.R.id.content)
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val heightDiff = rootView.height - (rect.bottom-rect.top)
    return heightDiff > 100
}

fun Activity.isKeyboardClosed() = !this.isKeyboardOpen()
