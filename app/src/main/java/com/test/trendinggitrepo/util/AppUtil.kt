package com.test.trendinggitrepo.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import java.text.ParseException
import java.text.SimpleDateFormat

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
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)
        }

        fun makeTimeStampReadable(serverTimeStamp: String): String {
            val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val displayFormat = SimpleDateFormat("MMM dd, yyyy")
            var date = ""
            try {
                date = displayFormat.format(serverFormat.parse(serverTimeStamp))
            } catch (e: ParseException) {
                e.printStackTrace();
            }
            return date
        }
    }
}