package com.deserve.photosearch.util

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
fun View.show(value: Boolean = true) {
    if (value) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Activity.showError(
    title: String, message: String,
    positiveButton: DialogButton? = null,
    negativeButton: DialogButton? = null
) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
    positiveButton?.run {
        builder.setPositiveButton(text) { _, _ ->
            listener?.invoke()
        }
    }
    negativeButton?.run {
        builder.setPositiveButton(text) { _, _ ->
            listener?.invoke()
        }
    }
    builder.show()
}

fun Activity.showError(
    title: Int,
    message: Int,
    positiveButton: DialogButton? = null,
    negativeButton: DialogButton? = null
) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
    positiveButton?.run {
        builder.setPositiveButton(text) { _, _ ->
            listener?.invoke()
        }
    }
    negativeButton?.run {
        builder.setPositiveButton(text) { _, _ ->
            listener?.invoke()
        }
    }
    builder.show()
}

class DialogButton(val text: String, val listener: (() -> Unit)? = null)