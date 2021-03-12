package com.uszkai.mealdeas.util

import android.util.Log
import com.uszkai.mealdeas.util.Constants.AppConstants.Companion.DEBUG
import com.uszkai.mealdeas.util.Constants.AppConstants.Companion.TAG

var isUnitTest = false

fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}

fun cLog(msg: String?) {
    msg?.let {
        if (!DEBUG) {
            //FirebaseCrashlytics.getInstance().log(it)
        }
    }
}

