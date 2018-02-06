package com.test.trendinggitrepo.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.Toast
import com.test.trendinggitrepo.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by alexgomes on 2018-02-05.
 */
class AppUtil {

    companion object {
        fun hasNetworkConnectivity(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

        fun openUrl(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }

        fun checkForInternet(context: Activity): Boolean {
            if (!AppUtil.hasNetworkConnectivity(context)) {
                Toast.makeText(context, context.getString(R.string.no_connectivity_message), Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun makeTimeStampReadable(serverTimeStamp: String): String {
            val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.CANADA)
            val displayFormat = SimpleDateFormat("MMM dd, yyyy", Locale.CANADA)
            var date = ""
            try {
                date = displayFormat.format(serverFormat.parse(serverTimeStamp))
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }
    }
}