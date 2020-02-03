package com.banks.accountsync

import android.accounts.Account
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast

class AccountSyncManager {
    companion object {
        const val TAG = "AccountSyncManager"

        // TYPE必须与account_preferences.xml中的TYPE保持一致
        const val ACCOUNT_TYPE = "com.banks.accountsync.account.type"


        @SuppressLint("MissingPermission")
        fun syncInit(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                var mAccountManager =
                    context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
                val accounts: Array<Account> =
                    mAccountManager.getAccountsByType(ACCOUNT_TYPE) // 获取系统帐户列表中已添加的帐户是否存在我们的帐户，用TYPE做为标识

                if (accounts.isNotEmpty()) {
                    if (BuildConfig.DEBUG) {
                        Toast.makeText(context, "已添加帐户", Toast.LENGTH_LONG).show()
                    }
                } else {
                    val account =
                        Account(context.resources.getString(R.string.app_name), ACCOUNT_TYPE)
                    mAccountManager.addAccountExplicitly(account, null, null) // 帐户密码和信息这里用null演示
                    if (BuildConfig.DEBUG) {
                        Toast.makeText(context, "正在添加帐户", Toast.LENGTH_LONG).show()
                    }


                    // 自动同步
                    val bundle = Bundle()
                    ContentResolver.setIsSyncable(account, AccountProvider.AUTHORITY, 1)
                    ContentResolver.setSyncAutomatically(account, AccountProvider.AUTHORITY, true)
                    ContentResolver.addPeriodicSync(account, AccountProvider.AUTHORITY, bundle, 30)
                }
            }
        }
    }
}