package com.banks.synctest

import android.Manifest
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.banks.accountsync.AccountSyncManager
import com.banks.accountsync.BuildConfig
import com.banks.accounttest.R

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"

    }

    private lateinit var mAccountManager: AccountManager
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AccountSyncManager.syncInit(this)
//        requestSdcardPermission()
    }

    fun requestSdcardPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                100
            )
        }
    }

}
