package com.banks.accountsync

import android.accounts.Account
import android.app.Service
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

class SyncService : Service() {
    private var sSyncAdapter: SyncAdapter? = null
    override fun onCreate() {
        synchronized(sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = SyncAdapter(applicationContext, true)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return sSyncAdapter!!.syncAdapterBinder
    }

    internal inner class SyncAdapter(context: Context?, autoInitialize: Boolean) :
        AbstractThreadedSyncAdapter(context, autoInitialize) {
        override fun onPerformSync(
            account: Account,
            extras: Bundle,
            authority: String,
            provider: ContentProviderClient,
            syncResult: SyncResult
        ) {
            //TODO 数据同步
//            recordSync(account, extras, authority)
            val clazz = Class.forName("com.banks.accountsync.AccountSyncAction")
            val newInstance = clazz.newInstance()
            val method = clazz.getMethod("doSync", Context::class.java)
            method.invoke(newInstance, this@SyncService)
        }
    }

    private fun recordSync(
        account: Account,
        extras: Bundle,
        authority: String
    ) {
        val externalFilesDir = getExternalFilesDir("record")
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "externalFilesDir $externalFilesDir")
        }

        val file = File(externalFilesDir, "recordSync.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val bufferWriter = BufferedWriter(OutputStreamWriter(FileOutputStream(file, true)))

        val pattern = "yyyy年MM月dd日 HH:mm:ss"
        val sdf = SimpleDateFormat(pattern)
        val currentTime = sdf.format(Date())
        val recordItem = "record sync --$currentTime"
        bufferWriter.write("$recordItem\n")
        bufferWriter.flush()
        bufferWriter.close()

        if (BuildConfig.DEBUG) {
            Log.v(TAG, "onPerformSync $currentTime")
        }
    }

    companion object {
        private const val TAG = "SyncService"
        private val sSyncAdapterLock = Any()
    }
}