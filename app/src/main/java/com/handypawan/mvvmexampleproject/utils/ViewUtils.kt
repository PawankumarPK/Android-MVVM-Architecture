package com.handypawan.mvvmexampleproject.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

/**
 * Created by pawan on 03,June,2020
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}