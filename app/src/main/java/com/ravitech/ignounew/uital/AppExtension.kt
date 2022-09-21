package com.ravitech.ignounew.uital

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun Context.xtnToast(message: String = "") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun xtnLog(message: String = "",tag: String= "TAGE") {
    Log.e(tag, message )
}

/**
 * @author Ravindra Singh
 * Extension method for show message SnackBar
 * */
fun View.showSnackBar(message: String) {
    val snackbar: Snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.show()
}
inline fun <reified T : Activity> Context.xtnNavigate(bundle: Bundle? = null) =
    startActivity(Intent(this, T::class.java).apply {
        if (bundle != null) putExtras(bundle)
    })

inline fun <reified T : Activity> Context.xtnNavigate2(bundle: Bundle? = null) =
    startActivity(Intent(this, T::class.java).apply {
      //      if (bundle != null) putExtras(bundle)
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    })

/**
 * @author Ravindra Singh
 * Extension method for hide key board
 */
fun View.hideKeyboard() {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Check internet connection
 *
 * @param context = context
 * @return
 */
fun Context.isNetworkAvailable(): Boolean {
    val cm =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    /*
    if (!isNetWorkAvailable && showAlert) {

        Dialogs().showAlert(context, context.getString(R.string.no_internet_connection_available))
    }
*/
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun Context.selectDate(block: (date: String) -> Unit) {
    val calender = Calendar.getInstance()
    DatePickerDialog(this,
        { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            //  viewModel.dataObserver.startData=cal.time
            block(cal.time.xtnFormatDDMMMYYYY())
            // binding.tvStartDatePlan.text=
        },
        calender.get(Calendar.YEAR),
        calender.get(Calendar.MONTH),
        calender.get(Calendar.DAY_OF_MONTH)).show()
    // return cal.time.xtnFormatDDMMMYYYY()
}

fun Date.xtnFormatDDMMMYYYY(format: String = DDMMYYY): String {
    val temp = SimpleDateFormat(format, Locale.getDefault())
    return temp.format(this)
}

inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {
    return try {
        action()
    } catch (e: Exception) {
        Resource.Error(e.message ?: "An unknown Error Occurred")
    }
}