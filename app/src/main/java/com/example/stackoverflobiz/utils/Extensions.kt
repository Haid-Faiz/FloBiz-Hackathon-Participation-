package com.example.stackoverflobiz.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.datasource.models.Question
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun View.showSnackBar(message: String, action: (() -> Unit)? = null, actionMsg: String? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        setAction(actionMsg) {
            action?.invoke()
        }
        show()
    }
}

fun Fragment.showSnackBar(
    message: String,
    action: (() -> Unit)? = null,
    actionMsg: String? = null
) = requireView().showSnackBar(message, action, actionMsg)

fun TextView.formatTime(inputTime: Long) {
    val date = Date(inputTime)
    // inputPatter: yyyy-MM-dd
    // Formatting parsed input time/date
    val formattedTime = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date)
    // Setting time to this textview
    this.text = formattedTime
}

fun Fragment.safeFragmentNavigation(
    navController: NavController,
    currentFragmentId: Int,
    actionId: Int,
) {
    if (navController.currentDestination?.id == currentFragmentId) {
        navController.navigate(actionId)
    }
}

fun Fragment.openBrowser(question: Question, context: Context)  {
    // Click listener for chrome custom tabs
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            .setShowTitle(true)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(question.link))
}