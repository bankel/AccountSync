package com.banks.accountsync

import android.content.Context
import android.util.Log
import com.banks.accounttest.BuildConfig

class AccountSyncAction {
    companion object{
        private const val TAG = "AccountSyncAction"
    }
    fun doSync(context: Context) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG,"doSync")
        }
    }
}